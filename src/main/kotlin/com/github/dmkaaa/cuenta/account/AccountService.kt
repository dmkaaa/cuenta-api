package com.github.dmkaaa.cuenta.account

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun getAll(): List<Account> {
        return accountRepository.findAll(Sort.by("code"))
    }
}