package com.github.dmkaaa.cuenta.entry

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.dmkaaa.cuenta.account.AccountResponse
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate

data class EntryRequest(
    val debitAccountId: Long,
    val creditAccountId: Long,
    @field:Positive
    @field:Digits(integer = 10, fraction = 2)
    val amount: BigDecimal,
    val date: LocalDate,
    @field:Size(max = 1000)
    @field:NotBlank
    val description: String,
) {
    init {
        require(debitAccountId != creditAccountId)
    }
}

data class EntryResponse(
    val id: Long,
    val debitAccount: AccountResponse,
    val creditAccount: AccountResponse,
    val amount: BigDecimal,
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
    val description: String,
)