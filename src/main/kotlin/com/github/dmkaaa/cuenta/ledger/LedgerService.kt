package com.github.dmkaaa.cuenta.ledger

import com.github.dmkaaa.cuenta.account.Account
import com.github.dmkaaa.cuenta.account.AccountType
import com.github.dmkaaa.cuenta.entry.Entry
import com.github.dmkaaa.cuenta.entry.EntryMapper
import com.github.dmkaaa.cuenta.entry.EntryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class LedgerService(
    private val entryRepository: EntryRepository,
    private val entryMapper: EntryMapper
) {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun get(): Ledger {
        val subLedgers = entryRepository.findAll()
            .groupByAccounts()
            .map { (account, entry) -> createSubLedger(account, entry) }

        return Ledger(subLedgers)
    }

    private fun createSubLedger(account: Account, entries: List<Entry>): SubLedger {
        val totalDebit = entries.filter { it.debitAccount == account }.getTotalAmount()
        val totalCredit = entries.filter { it.creditAccount == account }.getTotalAmount()

        return SubLedger(
            accountId = account.id!!,
            openingBalance = BigDecimal.ZERO,
            totalDebit = totalDebit,
            totalCredit = totalCredit,
            closingBalance = calculateBalance(account.type, totalDebit, totalCredit),
            entries = entryMapper.toDto(entries),
        )
    }

    private fun calculateBalance(accountType: AccountType, debit: BigDecimal, credit: BigDecimal): BigDecimal {
        return when (accountType) {
            AccountType.ASSET, AccountType.EXPENSE -> debit.minus(credit)
            AccountType.LIABILITY, AccountType.REVENUE -> credit.minus(debit)
        }
    }

    private fun List<Entry>.getTotalAmount(): BigDecimal {
        return map { it.amount }.fold(BigDecimal.ZERO) { acc, entry -> acc.add(entry) }
    }

    private fun List<Entry>.groupByAccounts(): Map<Account, List<Entry>> {
        val map = mutableMapOf<Account, MutableList<Entry>>()
        forEach { entry ->
            map.computeIfAbsent(entry.debitAccount!!) { _ -> mutableListOf() }.add(entry)
            map.computeIfAbsent(entry.creditAccount!!) { _ -> mutableListOf() }.add(entry)
        }
        return map
    }
}