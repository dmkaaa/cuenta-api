package com.github.dmkaaa.cuenta.entry

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate

data class EntryRequest(
    val debitAccountId: Long,
    val creditAccountId: Long,
    @field:Min(0)
    val amount: BigDecimal,
    val date: LocalDate,
    @field:Size(max = 1000)
    @field:NotBlank
    val description: String,
)