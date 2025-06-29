package com.example.userapp

import com.example.userapp.model.UserData
import com.example.userapp.service.UserService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * The @WebMvcTest loads only MVC-related beans (aka just the web/controller layer), instead of the
 * entire Spring beans. Also injects a MockMvc bean so we can send mock HTTP requests/responses to
 * the controller.
 */
@WebMvcTest
class UserControllerClassTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    // Replaces the userService bean with a mocked version so we don't use the real service
    @MockkBean
    private lateinit var userService: UserService

    @Test
    fun `should get all users`() {
        // Arrange
        val mockUsers = listOf(
            UserData(
                id = 1L,
                name = "Jane",
                gender = "Female",
                email = "janesemail@company.com"
            )
        )

        every { userService.getAllUsers() } returns mockUsers

        // Act, Assert
        mockMvc.get("/users").andExpect {
            status().isOk()
            content().json(
                """
                    [
                    	{
                    		"id": 1,
                    		"name": "Jane",
                    		"gender": "Female",
                    		"email": "janesemail@company.com"
                    	}
                    ]
                """.trimIndent()
            )
        }

        verify { userService.getAllUsers() } // Verify getAllUsers() fxn is called once
    }

    @Test
    fun `should delete user`() {
        // Arrange
        val userId = 1L

        every { userService.deleteUserById(userId) } just Runs // Mockk uses "just Runs" construct if fxn returns Unit

        // Act, Assert
        mockMvc.delete("/users/$userId").andExpect {
            status().isOk()
        }

        verify { userService.deleteUserById(userId) }
    }

    @Test
    fun `should create new user`() {
        // Arrange
        val inputUser = UserData(
            id = 1L,
            name = "Jane",
            gender = "Female",
            email = "janesemail@company.com"
        )
        val outputUser = inputUser.copy(id = 1L)

        every { userService.createNewUser(inputUser) } returns outputUser

        // Act, Assert
        mockMvc.post("/users") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(inputUser)
        } .andExpect {
            status().isOk()
            content().json(
                """
                    {
                        "id": 1,
                        "name": "Jane",
                        "gender": "Female",
                        "email": "janesemail@company.com"
                    }
                """.trimIndent()
            )
        }

        verify { userService.createNewUser(inputUser) }
    }

    @Test
    fun `should update user`() {
        // Arrange
        val userId = 1L
        val userToUpdate = UserData(
            email = "janesupdatedemail@company.com"
        )
        val updatedUser = UserData(
            id = userId,
            name = "Jane",
            gender = "Female",
            email = "janesupdatedemail@company.com"
        )

        every { userService.updateUserData(userId, userToUpdate) } returns updatedUser

        // Act, Assert
        mockMvc.put("/users/$userId") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(userToUpdate)
        } .andExpect {
            status().isOk()
            content().json(
                """
                    {
                        "id": 1,
                        "name": "Jane",
                        "gender": "Female",
                        "email": "janesupdatedemail@company.com"
                    }
                """.trimIndent()
            )
        }

        verify { userService.updateUserData(userId, userToUpdate) }
    }
}
