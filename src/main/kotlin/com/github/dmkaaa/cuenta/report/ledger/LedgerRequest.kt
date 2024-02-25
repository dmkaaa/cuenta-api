package com.github.dmkaaa.cuenta.report.ledger

import java.time.LocalDate

data class LedgerRequest(
    val periodStart: LocalDate,
    val periodEnd: LocalDate
)