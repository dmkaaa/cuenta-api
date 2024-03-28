package com.github.dmkaaa.cuenta.util

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class Period(
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val start: LocalDate,
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val end: LocalDate
)