package com.github.dmkaaa.cuenta.entry

import org.springframework.data.jpa.repository.JpaRepository

interface EntryRepository : JpaRepository<Entry, Long>