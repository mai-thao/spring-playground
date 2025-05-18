package com.example.userapp.controller

import com.example.userapp.model.UserData
import com.example.userapp.repository.UserRepository
import com.example.userapp.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserContoller(private val userRepo: UserRepository, private val userService: UserService) {
    @GetMapping
    fun getAll(): List<UserData> = userRepo.findAll()

    @PostMapping
    fun createUser(@RequestBody userData: UserData): UserData = userRepo.save(userData)

    @DeleteMapping
    fun deleteUser(@RequestParam id: Long) = userRepo.deleteById(id)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userData: UserData): UserData {
        return userService.updateUserData(id, userData)
    }
}
