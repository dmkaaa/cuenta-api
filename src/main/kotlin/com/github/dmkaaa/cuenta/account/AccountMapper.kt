package com.github.dmkaaa.cuenta.account

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.MappingTarget

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccountMapper {

    fun toDto(accounts: List<Account>): List<AccountDto>
    fun toDto(accounts: Account): AccountDto

    @Mapping(target = "id", ignore = true)
    fun fromDto(request: AccountDto): Account

    @Mapping(target = "id", ignore = true)
    fun fromDto(@MappingTarget account: Account, request: AccountDto)
}