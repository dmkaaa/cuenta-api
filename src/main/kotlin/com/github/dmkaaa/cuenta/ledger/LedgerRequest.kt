package com.github.dmkaaa.cuenta.ledger

import java.time.LocalDate

data class LedgerRequest(
    val periodStart: LocalDate,
    val periodEnd: LocalDate
)