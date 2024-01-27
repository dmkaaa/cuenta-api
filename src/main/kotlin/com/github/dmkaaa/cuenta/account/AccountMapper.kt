package com.github.dmkaaa.cuenta.account

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.MappingTarget

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccountMapper {

    fun toResponse(accounts: List<Account>): List<AccountResponse>
    fun toResponse(accounts: Account): AccountResponse

    @Mapping(target = "id", ignore = true)
    fun fromRequest(request: AccountRequest): Account

    @Mapping(target = "id", ignore = true)
    fun fromRequest(@MappingTarget account: Account, request: AccountRequest)
}