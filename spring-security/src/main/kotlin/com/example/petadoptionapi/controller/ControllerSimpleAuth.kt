package com.example.petadoptionapi.controller

import com.example.petadoptionapi.model.AdoptionStatus
import com.example.petadoptionapi.model.Pet
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
class PetAdoptionController {
    @GetMapping
    fun getAll(): List<Pet> = TODO()

    @PostMapping
    fun createPet(@RequestBody petInfo: Pet): Pet = TODO()

    @PutMapping("/{id}")
    fun updatePet(@PathVariable id: Long, @RequestBody petInfo: Pet): Pet {
        TODO()
    }

    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable id: Long): Nothing = TODO()

    private val pets = mutableListOf(
        Pet(4827192342, "Lucy", 7, "Husky", AdoptionStatus.AVAILABLE),
        Pet(9481729301, "Buddy", 2, "Husky", AdoptionStatus.AVAILABLE),
        Pet(7182346192, "Lucky", 4, "Husky", AdoptionStatus.AVAILABLE)
    )
}
