package com.example.userapp.controller

import com.example.userapp.model.User
import com.example.userapp.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserContoller(private val userRepo: UserRepository) {
    @GetMapping
    fun getAll(): List<User> = userRepo.findAll()

    @PostMapping
    fun createUser(@RequestBody user: User): User = userRepo.save(user)
}
