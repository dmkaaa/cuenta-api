package com.github.dmkaaa.cuenta.account

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/accounts")
class AccountController(private val accountService: AccountService) {

    @GetMapping
    fun getAll(): List<AccountResponse> {
        return accountService.getAll()
    }

    @PostMapping
    fun create(@Valid @RequestBody request: AccountRequest): AccountResponse {
        return accountService.create(request)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: AccountRequest): AccountResponse {
        return accountService.update(id, request)
    }
}