package com.github.dmkaaa.cuenta.account

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository, val accountMapper: AccountMapper) {

    fun getAll(): List<AccountResponse> {
        val accounts = accountRepository.findAll(Sort.by("code"))
        return accountMapper.toResponse(accounts)
    }

    fun create(request: AccountRequest): AccountResponse {
        var account = accountMapper.fromRequest(request)
        account = accountRepository.save(account)
        return accountMapper.toResponse(account)
    }

    fun update(id: Long, request: AccountRequest): AccountResponse {
        val account = accountRepository.findById(id).orElseThrow()
        accountMapper.fromRequest(account, request)
        accountRepository.save(account)
        return accountMapper.toResponse(account)
    }
}