package com.github.dmkaaa.cuenta.report.balance

import com.github.dmkaaa.cuenta.report.AccountBalance

data class BalanceSheet(val activus: BalanceSheetSide, val passivus: BalanceSheetSide)

data class BalanceSheetSide(val rows: List<BalanceSheetRow>) {
    val total = rows.sumOf { it.balance.value }
}

data class BalanceSheetRow(val accountId: Long, val balance: AccountBalance)