package com.github.dmkaaa.cuenta.ledger

import com.github.dmkaaa.cuenta.entry.EntryDto
import java.math.BigDecimal

data class Ledger(val subLedgers: List<SubLedger>)

data class SubLedger(
    val accountId: Long,
    val openingBalance: BigDecimal,
    val closingBalance: BigDecimal,
    val entries: List<EntryDto>,
    val totalDebit: BigDecimal,
    val totalCredit: BigDecimal
)