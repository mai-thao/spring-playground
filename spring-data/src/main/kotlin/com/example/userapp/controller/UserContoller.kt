package com.example.userapp.controller

import com.example.userapp.model.UserData
import com.example.userapp.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserContoller(private val userService: UserService) {
    @GetMapping
    fun getAll(): List<UserData> = userService.getAllUsers()

    @PostMapping
    fun createUser(@RequestBody userData: UserData): UserData = userService.createNewUser(userData)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteUserById(id)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userData: UserData): UserData {
        return userService.updateUserData(id, userData)
    }
}
