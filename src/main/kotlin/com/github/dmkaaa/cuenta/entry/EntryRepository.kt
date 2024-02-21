package com.github.dmkaaa.cuenta.entry

import com.github.dmkaaa.cuenta.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface EntryRepository : JpaRepository<Entry, Long> {

    @Query("select e from Entry e where (debitAccount = ?1 or creditAccount = ?1) and date between ?2 and ?3")
    fun findByAccount(account: Account, start: LocalDate, end: LocalDate): List<Entry>

    @Query("select e from Entry e where (debitAccount = ?1 or creditAccount = ?1) and date <= ?2")
    fun findByAccount(account: Account, end: LocalDate): List<Entry>
}