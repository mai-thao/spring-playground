package com.example.petadoptionapi

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc // Both annotations load the full Spring context so we can test security and @PreAuthorize configurations
class SimpleAuthControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `GET endpoints accessible without auth`() {
        mockMvc.get("/pets/123")
            .andExpect {
                status { isOk() }
            }

        mockMvc.get("/pets")
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `POST endpoint return 401 without auth`() {
        mockMvc.post("/pets")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun `PUT endpoint return 401 without auth`() {
        mockMvc.put("/pets/123")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun `DELETE endpoints return 401 without auth`() {
        mockMvc.delete("/pets/123")
            .andExpect {
                status { isUnauthorized() }
            }

        mockMvc.delete("/pets")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun `POST allow authenticated users`() {
        val reqBody =
            """
            {
                "id": 101,
                "name": "TEST",
                "age": 6,
                "breed": "TEST",
                "gender": "TEST"
            }
            """.trimIndent()

        mockMvc.post("/pets") {
            contentType = MediaType.APPLICATION_JSON
            content = reqBody
        }.andExpect {
            status { isCreated() }
        }
    }
}