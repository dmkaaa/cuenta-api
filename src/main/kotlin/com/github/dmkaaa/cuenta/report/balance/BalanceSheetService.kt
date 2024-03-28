package com.github.dmkaaa.cuenta.report.balance

import com.github.dmkaaa.cuenta.account.AccountRepository
import com.github.dmkaaa.cuenta.account.AccountType
import com.github.dmkaaa.cuenta.entry.EntryRepository
import com.github.dmkaaa.cuenta.report.calculateBalance
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class BalanceSheetService(
    private val accountRepository: AccountRepository,
    private val entryRepository: EntryRepository
) {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun get(date: LocalDate): BalanceSheet {
        return BalanceSheet(
            date = date,
            activus = getSide(date, listOf(AccountType.ASSET)),
            passivus = getSide(date, listOf(AccountType.LIABILITY, AccountType.EQUITY))
        )
    }

    private fun getSide(date: LocalDate, accountTypes: List<AccountType>): BalanceSheetSide {
        val rows = accountRepository
            .findByTypeIn(accountTypes)
            .map { account ->
                val entries = entryRepository.findByAccount(account, date)
                BalanceSheetRow(
                    accountId = account.id!!,
                    balance = entries.calculateBalance(account)
                )
            }

        return BalanceSheetSide(rows)
    }
}