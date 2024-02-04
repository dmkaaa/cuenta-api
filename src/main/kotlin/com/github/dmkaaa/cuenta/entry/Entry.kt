package com.github.dmkaaa.cuenta.entry

import com.github.dmkaaa.cuenta.account.Account
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @ManyToOne
    @JoinColumn(name = "debit_account_id", nullable = false)
    var debitAccount: Account?,
    @ManyToOne
    @JoinColumn(name = "credit_account_id", nullable = false)
    var creditAccount: Account?,
    var amount: BigDecimal,
    var date: LocalDate,
    var description: String,
)