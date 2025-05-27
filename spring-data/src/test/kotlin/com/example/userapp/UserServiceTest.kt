package com.example.userapp

import com.example.userapp.model.UserData
import com.example.userapp.repository.UserRepository
import com.example.userapp.service.UserService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

/**
 * FYI: We are not testing the getAllUsers(), createNewUser(), or deleteUserById() fxns
 * because they are pure delegations and have no business logic to verify. Unlike the
 * updateUserData() fxn which has the logic to search for an existing user first.
 */
class UserServiceTest {
    // Mock the UserRepository so we don't use the real repository or database
    @MockK
    private lateinit var userRepo: UserRepository

    // Inject necessary mocks (like userRepo) into this instance of UserService
    @InjectMockKs
    private lateinit var userService: UserService

    // Set up the mocks before each test runs
    @BeforeEach
    fun initMocks() {
        // Initialize all Mockk annotations
        MockKAnnotations.init(this)
    }

    @Test
    fun `should update existing user`() {
        // Arrange
        val userId = 1L
        val updateUserRequest = UserData(
            gender = "Male",
            email = "janesupdatedemail@company.com"
        )
        val existingUser = UserData(
            id = 1L,
            name = "Jane",
            gender = "Female",
            email = "janesemail@company.com"
        )
        val updatedUser = existingUser.copy(
            gender = "Male",
            email = "janesupdatedemail@company.com"
        )

        every { userRepo.findById(userId) } returns Optional.of(existingUser)
        every { userRepo.save(updatedUser) } returns updatedUser

        // Act
        val returnedUser = userService.updateUserData(userId, updateUserRequest)

        // Assert
        assertEquals(returnedUser, updatedUser)

        verifyOrder { // Verify the correct order that userRepo functions were called in
            userRepo.findById(userId)
            userRepo.save(returnedUser)
        }
        verify(exactly = 1) { userRepo.findById(userId) }
        verify(exactly = 1) { userRepo.save(returnedUser) }
    }

    @Test
    fun `should throw exception if user does not exist`() {
        // Arrange
        val userId = 10L
        val updateUserData = UserData(
            id = userId
        )
        every { userRepo.findById(userId) } returns Optional.empty()

        // Act
        val exception = assertThrows<ResponseStatusException> {
            userService.updateUserData(userId, updateUserData)
        }

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("User with id $userId not found", exception.reason)

        verify(exactly = 1) { userRepo.findById(userId) }
        verify(exactly = 0) { userRepo.save(any()) }
    }
}
