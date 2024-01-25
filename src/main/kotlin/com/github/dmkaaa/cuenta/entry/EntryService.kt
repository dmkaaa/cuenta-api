package com.github.dmkaaa.cuenta.entry

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EntryService(private val entryRepository: EntryRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)
    fun createBulk(request: List<EntryRequest>) {
        logger.info("Request: {}", request)
    }
}