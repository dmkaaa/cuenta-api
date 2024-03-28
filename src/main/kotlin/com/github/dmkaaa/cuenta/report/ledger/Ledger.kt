package com.github.dmkaaa.cuenta.report.ledger

import com.github.dmkaaa.cuenta.entry.EntryDto
import com.github.dmkaaa.cuenta.report.AccountBalance
import com.github.dmkaaa.cuenta.util.Period

data class Ledger(
    val period: Period,
    val subLedgers: List<SubLedger>
)

data class SubLedger(
    val accountId: Long,
    val openingBalance: AccountBalance,
    val balance: AccountBalance,
    val entries: List<EntryDto>,
) {
    val closingBalance = openingBalance + balance
}