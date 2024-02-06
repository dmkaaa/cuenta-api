package com.github.dmkaaa.cuenta.entry

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.MappingTarget

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface EntryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "debitAccount", ignore = true)
    @Mapping(target = "creditAccount", ignore = true)
    fun fromDto(dto: EntryDto): Entry

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "debitAccount", ignore = true)
    @Mapping(target = "creditAccount", ignore = true)
    fun fromDto(@MappingTarget entry: Entry, dto: EntryDto)

    @Mapping(target = "debitAccountId", source = "debitAccount.id")
    @Mapping(target = "creditAccountId", source = "creditAccount.id")
    fun toDto(entries: Entry): EntryDto

    fun toDto(entries: List<Entry>): List<EntryDto>
}