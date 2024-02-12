package com.github.dmkaaa.cuenta.ledger

import com.github.dmkaaa.cuenta.entry.EntryRepository
import org.springframework.stereotype.Service

@Service
class LedgerService(private val entryRepository: EntryRepository) {

    fun get(): LedgerDto {
        return LedgerDto(emptyList())
    }
}