package com.github.dmkaaa.cuenta.report.ledger

import com.github.dmkaaa.cuenta.account.AccountRepository
import com.github.dmkaaa.cuenta.entry.EntryMapper
import com.github.dmkaaa.cuenta.entry.EntryRepository
import com.github.dmkaaa.cuenta.report.calculateBalance
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

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
            val openingBalance = previousEntries.calculateBalance(account)

            SubLedger(
                accountId = account.id!!,
                openingBalance = openingBalance,
                balance = entries.calculateBalance(account),
                entries = entryMapper.toDto(entries),
            )
        }

        return Ledger(subLedgers)
    }
}