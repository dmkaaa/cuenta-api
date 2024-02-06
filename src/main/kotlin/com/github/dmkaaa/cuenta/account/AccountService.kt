package com.github.dmkaaa.cuenta.account

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository, private val accountMapper: AccountMapper) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getAll(): List<AccountDto> {
        val accounts = accountRepository.findAll(Sort.by("code"))
        return accountMapper.toDto(accounts)
    }

    fun create(request: AccountDto): AccountDto {
        logger.info("Creating account: {}", request)
        var account = accountMapper.fromDto(request)
        account = accountRepository.save(account)
        return accountMapper.toDto(account)
    }

    fun update(id: Long, request: AccountDto): AccountDto {
        logger.info("Updating account {}: {}", id, request)
        val account = accountRepository.findById(id).orElseThrow()
        accountMapper.fromDto(account, request)
        accountRepository.save(account)
        return accountMapper.toDto(account)
    }
}