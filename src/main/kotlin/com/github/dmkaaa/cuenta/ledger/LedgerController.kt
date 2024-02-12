package com.github.dmkaaa.cuenta.ledger

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/ledger")
class LedgerController(private val ledgerService: LedgerService) {

    @GetMapping
    fun get(): LedgerDto {
        return ledgerService.get()
    }
}