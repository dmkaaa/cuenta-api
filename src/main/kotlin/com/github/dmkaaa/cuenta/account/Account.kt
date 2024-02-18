package com.github.dmkaaa.cuenta.account

import jakarta.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var code: String,
    @Enumerated(EnumType.STRING)
    var type: AccountType,
    var name: String,
)

enum class AccountType {
    ASSET, LIABILITY, REVENUE, EXPENSE
}