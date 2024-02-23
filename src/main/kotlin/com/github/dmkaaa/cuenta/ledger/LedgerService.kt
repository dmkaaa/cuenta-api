package com.github.dmkaaa.cuenta.ledger

import com.github.dmkaaa.cuenta.account.Account
import com.github.dmkaaa.cuenta.account.AccountRepository
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
    private val accountRepository: AccountRepository,
    private val entryRepository: EntryRepository,
    private val entryMapper: EntryMapper
) {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun get(request: LedgerRequest): Ledger {
        val subLedgers = accountRepository.findAll().map { account ->
            val previousEntries = entryRepository.findByAccount(account, request.periodStart.minusDays(1))
            val entries = entryRepository.findByAccount(account, request.periodStart, request.periodEnd)
            val previousPeriodSubLedger = createSubLedger(account, previousEntries, BigDecimal.ZERO)
            createSubLedger(account, entries, previousPeriodSubLedger.closingBalance)
        }

        return Ledger(subLedgers)
    }

    private fun createSubLedger(account: Account, entries: List<Entry>, openingBalance: BigDecimal): SubLedger {
        val totalDebit = entries.filter { it.debitAccount == account }.getTotalAmount()
        val totalCredit = entries.filter { it.creditAccount == account }.getTotalAmount()
        val balance = calculateBalance(account.type, totalDebit, totalCredit)

        return SubLedger(
            accountId = account.id!!,
            openingBalance = openingBalance,
            totalDebit = totalDebit,
            totalCredit = totalCredit,
            closingBalance = openingBalance.add(balance),
            entries = entryMapper.toDto(entries),
        )
    }

    private fun calculateBalance(accountType: AccountType, debit: BigDecimal, credit: BigDecimal): BigDecimal {
        return when (accountType) {
            AccountType.ASSET, AccountType.EXPENSE -> debit.minus(credit)
            AccountType.LIABILITY, AccountType.EQUITY, AccountType.REVENUE, AccountType.INCOME -> credit.minus(debit)
        }
    }

    private fun List<Entry>.getTotalAmount(): BigDecimal {
        return map { it.amount }.fold(BigDecimal.ZERO) { acc, entry -> acc.add(entry) }
    }
}