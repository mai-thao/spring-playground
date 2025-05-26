package com.example.userapp

import com.example.userapp.model.UserData
import com.example.userapp.service.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class UserControllerClassTest(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    private lateinit var userService: UserService

    @Test
    fun `should get all users`() {
        // Arrange
        val mockUsers = listOf(
            UserData(
                id = 1,
                name = "Jane",
                gender = "Female",
                email = "janesemail@company.com"
            )
        )

        // Act
        every {userService.getAllUsers()} returns mockUsers

        // Assert
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk)
            .andExpect(content().json(
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
            ))

        verify { userService.getAllUsers() }
    }
}
