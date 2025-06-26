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
    @WithMockUser(roles = ["USER"]) // Important annotation to test authentication! See: https://docs.spring.io/spring-security/reference/servlet/test/method.html#test-method-withmockuser
    fun `POST and PUT allow authenticated users`() {
        val postReqBody =
            """
            {
                "id": 101,
                "name": "TEST",
                "age": 6,
                "breed": "TEST",
                "gender": "TEST"
            }
            """.trimIndent()

        val putReqBody =
            """
            {
                "id": 123,
                "name": "TEST",
                "age": 6,
                "breed": "TEST",
                "gender": "TEST"
            }
            """.trimIndent()

        mockMvc.post("/pets") {
            contentType = MediaType.APPLICATION_JSON
            content = postReqBody
        }.andExpect {
            status { isCreated() }
        }

        mockMvc.put("/pets/123") {
            contentType = MediaType.APPLICATION_JSON
            content = putReqBody
        }.andExpect {
            status { isOk() }
        }
    }
}