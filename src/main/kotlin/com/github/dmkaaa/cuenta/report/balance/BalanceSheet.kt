package com.github.dmkaaa.cuenta.report.balance

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.dmkaaa.cuenta.report.AccountBalance
import java.time.LocalDate

data class BalanceSheet(
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
    val activus: BalanceSheetSide,
    val passivus: BalanceSheetSide
)

data class BalanceSheetSide(val rows: List<BalanceSheetRow>) {
    val total = rows.sumOf { it.balance.value }
}

data class BalanceSheetRow(val accountId: Long, val balance: AccountBalance)