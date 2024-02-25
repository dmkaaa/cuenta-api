package com.github.dmkaaa.cuenta.report

import com.github.dmkaaa.cuenta.account.Account
import com.github.dmkaaa.cuenta.entry.Entry
import java.math.BigDecimal

fun List<Entry>.calculateBalance(account: Account): AccountBalance {
    val totalDebit = filter { it.debitAccount == account }.getTotalAmount()
    val totalCredit = filter { it.creditAccount == account }.getTotalAmount()
    return AccountBalance(account.type, totalDebit, totalCredit)
}

fun List<Entry>.getTotalAmount(): BigDecimal {
    return map { it.amount }.fold(BigDecimal.ZERO) { acc, entry -> acc.add(entry) }
}