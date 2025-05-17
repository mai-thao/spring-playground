package com.example.userapp.controller

import com.example.userapp.model.UserData
import com.example.userapp.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserContoller(private val userRepo: UserRepository) {
    @GetMapping
    fun getAll(): List<UserData> = userRepo.findAll()

    @PostMapping
    fun createUser(@RequestBody userData: UserData): UserData = userRepo.save(userData)
}
