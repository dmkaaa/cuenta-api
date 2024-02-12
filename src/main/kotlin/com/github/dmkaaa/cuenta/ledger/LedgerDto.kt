package com.github.dmkaaa.cuenta.ledger

import com.github.dmkaaa.cuenta.account.AccountDto
import com.github.dmkaaa.cuenta.entry.EntryDto
import java.math.BigDecimal

data class LedgerDto(val subLedgers: List<SubLedgerDto>)

data class SubLedgerDto(
    val accountDto: AccountDto,
    val openingBalance: BigDecimal,
    val closingBalance: BigDecimal,
    val entries: List<EntryDto>,
    val debitTotal: BigDecimal,
    val creditTotal: BigDecimal
)