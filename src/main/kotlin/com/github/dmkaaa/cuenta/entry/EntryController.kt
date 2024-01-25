package com.github.dmkaaa.cuenta.entry

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/entries")
class EntryController(private val entryService: EntryService) {

    @PostMapping("/bulk")
    fun createBulk(@Valid @RequestBody request: List<@Valid EntryRequest>) {
        entryService.createBulk(request)
    }
}