package com.github.dmkaaa.cuenta.report

import com.github.dmkaaa.cuenta.account.AccountType
import java.math.BigDecimal

data class AccountBalance(val accountType: AccountType, val debit: BigDecimal, val credit: BigDecimal) {
    val value: BigDecimal = when (accountType) {
        AccountType.ASSET, AccountType.EXPENSE -> debit.minus(credit)
        AccountType.LIABILITY, AccountType.EQUITY, AccountType.REVENUE, AccountType.INCOME -> credit.minus(debit)
    }

    operator fun plus(other: AccountBalance): AccountBalance {
        require(accountType == other.accountType) { "Account type must match" }
        return AccountBalance(accountType, debit + other.debit, credit + other.credit)
    }
}