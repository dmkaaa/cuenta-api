package com.github.dmkaaa.cuenta.account

import jakarta.validation.constraints.NotBlank

data class AccountDto(
    val id: Long?,
    @field:NotBlank
    val code: String,
    val type: AccountType,
    @field:NotBlank
    val name: String,
)