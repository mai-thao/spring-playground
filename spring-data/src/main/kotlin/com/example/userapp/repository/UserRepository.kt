package com.example.userapp.repository

import com.example.userapp.model.UserData
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserData, Long>
