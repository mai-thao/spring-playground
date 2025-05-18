package com.example.userapp.service

import com.example.userapp.model.UserData
import com.example.userapp.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(private val userRepo: UserRepository) {
    fun updateUserData(id: Long, userData: UserData): UserData {
        val existingUser = userRepo.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found")
        }
        if (userData.name.isNotBlank()) {
            existingUser.name = userData.name
        }
        if (userData.gender.isNotBlank()) {
            existingUser.gender = userData.gender
        }
        if (userData.email.isNotBlank()) {
            existingUser.email = userData.email
        }
        return userRepo.save(existingUser)
    }
}