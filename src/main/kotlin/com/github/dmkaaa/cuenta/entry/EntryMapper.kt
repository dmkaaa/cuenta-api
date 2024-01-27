package com.github.dmkaaa.cuenta.entry

import com.github.dmkaaa.cuenta.account.AccountMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = [AccountMapper::class])
interface EntryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "debitAccount", ignore = true)
    @Mapping(target = "creditAccount", ignore = true)
    fun fromRequest(request: EntryRequest): Entry

    fun toResponse(entries: List<Entry>): List<EntryResponse>
}