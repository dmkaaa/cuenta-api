package com.github.dmkaaa.cuenta.entry

import com.github.dmkaaa.cuenta.account.AccountRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class EntryService(
    private val entryRepository: EntryRepository,
    private val accountRepository: AccountRepository,
    private val entryMapper: EntryMapper
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun getList(): List<EntryResponse> {
        val entries = entryRepository.findAll()
        return entryMapper.toResponse(entries)
    }

    @Transactional
    fun createBulk(request: List<EntryRequest>): List<EntryResponse> {
        logger.info("Creating entries: {}", request)
        val entries = request.map {
            val entry = entryMapper.fromRequest(it)
            entry.creditAccount = accountRepository.findById(it.creditAccountId).orElseThrow()
            entry.debitAccount = accountRepository.findById(it.debitAccountId).orElseThrow()
            entry
        }

        val savedEntities = entryRepository.saveAll(entries)
        return entryMapper.toResponse(savedEntities)
    }

    fun delete(id: Long) {
        logger.info("Deleting entry {}", id)
        entryRepository.deleteById(id)
    }
}