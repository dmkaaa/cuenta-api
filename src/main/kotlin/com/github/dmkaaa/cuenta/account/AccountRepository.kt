package com.github.dmkaaa.cuenta.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    fun findByTypeIn(types: List<AccountType>): List<Account>
}