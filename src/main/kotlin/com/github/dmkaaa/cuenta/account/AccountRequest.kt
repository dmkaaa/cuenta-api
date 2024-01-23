package com.github.dmkaaa.cuenta.account

import jakarta.validation.constraints.NotBlank

data class AccountRequest(
    @field:NotBlank
    val code: String,
    val type: AccountType,
    @field:NotBlank
    val name: String,
)

data class AccountResponse(
    val id: Long,
    val code: String,
    val type: AccountType,
    val name: String,
)