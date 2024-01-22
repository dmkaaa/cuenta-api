package com.github.dmkaaa.cuenta.account

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/accounts")
class AccountController(private val accountService: AccountService) {

    @GetMapping
    fun getAll(): List<Account> {
        return accountService.getAll()
    }
}