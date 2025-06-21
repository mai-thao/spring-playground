package com.example.petadoptionapi.controller

import com.example.petadoptionapi.model.Pet
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
@EnableMethodSecurity
class SimpleAuthController {
    @GetMapping("/{id}")
    fun getPet(@PathVariable id: Long): ResponseEntity<Pet> {
        val existingPet = pets.firstOrNull { it.id == id }
        return if (existingPet != null) {
            ResponseEntity.ok(existingPet)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Pet>> = ResponseEntity.ok(pets)

    @PostMapping
    fun createPet(@RequestBody newPet: Pet): ResponseEntity<Any> {
        if (pets.any { it.id == newPet.id }) {
            return ResponseEntity.badRequest().body("ID already exists!")
        }
        pets.add(newPet)
        return ResponseEntity.status(HttpStatus.CREATED).body(newPet)
    }

    @PutMapping("/{id}")
    fun updatePet(@PathVariable id: Long, @RequestBody newPet: Pet): ResponseEntity<Pet> {
        val existingPetIdx = pets.indexOfFirst { newPet.id == id }
        if (existingPetIdx == -1) {
            return ResponseEntity.notFound().build()
        } else {
            pets[existingPetIdx] = newPet
            return ResponseEntity.ok(newPet)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable id: Long): ResponseEntity<Nothing> {
        val existingPetIdx = pets.removeIf { it.id == id }
        return if (existingPetIdx) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')") // Granular level restricting this DELETE method to only ADMINs
    fun deleteAllPet(): ResponseEntity<Nothing> {
        pets.clear()
        return ResponseEntity.noContent().build()
    }

    // List of sample existing pets
    private val pets = mutableListOf(
        Pet(123, "Lucy", 7, "Husky", "Female"),
        Pet(456, "Buddy", 2, "German Shepherd", "Male"),
        Pet(789, "Lucky", 4, "Husky", "Male")
    )
}
