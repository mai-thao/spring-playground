package com.example.petadoptionapi.controller

import com.example.petadoptionapi.model.Pet
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
class PetAdoptionController() {
    @GetMapping("/{id}")
    fun getPet(@PathVariable id: Long): Pet = TODO()

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
}
