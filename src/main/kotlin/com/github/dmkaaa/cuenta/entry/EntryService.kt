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
    fun getList(): List<EntryDto> {
        val entries = entryRepository.findAll()
        return entryMapper.toDto(entries)
    }

    @Transactional
    fun createBulk(request: List<EntryDto>): List<EntryDto> {
        logger.info("Creating entries: {}", request)
        val entries = request.map {
            val entry = entryMapper.fromDto(it)
            entry.creditAccount = accountRepository.findById(it.creditAccountId).orElseThrow()
            entry.debitAccount = accountRepository.findById(it.debitAccountId).orElseThrow()
            entry
        }

        val savedEntities = entryRepository.saveAll(entries)
        return entryMapper.toDto(savedEntities)
    }

    fun update(id: Long, request: EntryDto): EntryDto {
        val entry = entryRepository.findById(id).orElseThrow()
        entryMapper.fromDto(entry, request)
        entry.creditAccount = accountRepository.findById(request.creditAccountId).orElseThrow()
        entry.debitAccount = accountRepository.findById(request.debitAccountId).orElseThrow()
        val savedEntry = entryRepository.save(entry)
        return entryMapper.toDto(savedEntry)
    }

    fun delete(id: Long) {
        logger.info("Deleting entry {}", id)
        entryRepository.deleteById(id)
    }
}