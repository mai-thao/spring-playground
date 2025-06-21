package com.example.petadoptionapi.controller

import com.example.petadoptionapi.model.Pet
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
class ControllerSimpleAuth {
    @GetMapping("/{id}")
    fun getPet(@PathVariable id: Long): ResponseEntity<Pet> {
        val existingPet = pets.firstOrNull { it.id == id }
        if (existingPet != null) {
            return ResponseEntity.ok(existingPet)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Pet>> = ResponseEntity.ok(pets)

    @PostMapping
    fun createPet(@RequestBody newPet: Pet): ResponseEntity<Pet> {
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
        if (existingPetIdx) {
            return ResponseEntity.noContent().build()
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    // List of sample existing pets
    private val pets = mutableListOf(
        Pet(123, "Lucy", 7, "Husky", "Female"),
        Pet(456, "Buddy", 2, "German Shepherd", "Male"),
        Pet(789, "Lucky", 4, "Husky", "Male")
    )
}
