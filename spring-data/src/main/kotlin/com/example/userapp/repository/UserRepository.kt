package com.example.userapp.repository

import com.example.userapp.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>