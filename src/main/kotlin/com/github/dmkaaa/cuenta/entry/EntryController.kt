package com.github.dmkaaa.cuenta.entry

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/entries")
class EntryController(private val entryService: EntryService) {

    @GetMapping
    fun getList(): List<EntryResponse> {
        return entryService.getList()
    }

    @PostMapping("/bulk")
    fun createBulk(@Valid @RequestBody request: List<@Valid EntryRequest>): List<EntryResponse> {
        return entryService.createBulk(request)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: EntryRequest): EntryResponse {
        return entryService.update(id, request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        entryService.delete(id)
        return ResponseEntity.noContent().build()
    }
}