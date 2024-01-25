package com.github.dmkaaa.cuenta.account

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository, private val accountMapper: AccountMapper) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getAll(): List<AccountResponse> {
        val accounts = accountRepository.findAll(Sort.by("code"))
        return accountMapper.toResponse(accounts)
    }

    fun create(request: AccountRequest): AccountResponse {
        logger.info("Creating account: {}", request)
        var account = accountMapper.fromRequest(request)
        account = accountRepository.save(account)
        return accountMapper.toResponse(account)
    }

    fun update(id: Long, request: AccountRequest): AccountResponse {
        logger.info("Updating account {}: {}", id, request)
        val account = accountRepository.findById(id).orElseThrow()
        accountMapper.fromRequest(account, request)
        accountRepository.save(account)
        return accountMapper.toResponse(account)
    }
}