package com.github.dmkaaa.cuenta.entry

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate

data class EntryDto(
    val id: Long?,
    val debitAccountId: Long,
    val creditAccountId: Long,
    @field:Positive(message = "Invalid amount")
    @field:Digits(integer = 10, fraction = 2, message = "Invalid amount")
    val amount: BigDecimal,
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
    @field:Size(max = 1000, message = "Description must not exceed 1000 characters")
    @field:NotBlank(message = "Description must not be blank")
    val description: String,
) {
    init {
        require(debitAccountId != creditAccountId) { "Debit and credit account must be different" }
    }
}