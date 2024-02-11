package com.github.dmkaaa.cuenta.account

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AccountDto(
    val id: Long?,
    @field:NotBlank(message = "Code must not be blank")
    @field:Size(max = 10, message = "Code must not exceed 10 characters")
    val code: String,
    val type: AccountType,
    @field:NotBlank(message = "Name must not be blank")
    @field:Size(max = 100, message = "Code must not exceed 100 characters")
    val name: String,
)