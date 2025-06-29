package com.example.petadoptionapi

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

/**
 * This controller test only verifies the different Spring Security access control for different roles. It only
 * asserts on HTTP status codes and does not assert on the request or return bodies nor internal logic (e.g. whether
 * a pet exists or not). The goal is to demonstrate the Spring Security authorization logic only. For examples of
 * assertion on business logic or data correctness, please see my "spring-data" repo!
 */
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
    fun `POST and PUT endpoints allow authenticated users`() {
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

    @Test
    @WithMockUser(roles = ["USER"])
    fun `DELETE endpoints return 403 forbidden for users`() {
        mockMvc.delete("/pets/123")
            .andExpect {
                status { isForbidden() }
            }

        mockMvc.delete("/pets")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    @DirtiesContext // Force Spring to reset the context so the next test can delete the same endpoint again with a fresh context, see: https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-dirtiescontext.html
    @WithMockUser(roles = ["MANAGER"])
    fun `DELETE a pet endpoint requires at least manager role`() {
        mockMvc.delete("/pets/123")
            .andExpect {
                status { isNoContent() }
            }
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun `DELETE a pet endpoint accessible to admins too`() {
        mockMvc.delete("/pets/123")
            .andExpect {
                status { isNoContent() }
            }
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun `DELETE all pets endpoint accessible to admins only`() {
        mockMvc.delete("/pets")
            .andExpect {
                status { isNoContent() }
            }
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun `DELETE all pets endpoint returns 403 forbidden to users`() {
        mockMvc.delete("/pets")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    @WithMockUser(roles = ["MANAGER"])
    fun `DELETE all pets endpoint returns 403 forbidden to managers`() {
        mockMvc.delete("/pets")
            .andExpect {
                status { isForbidden() }
            }
    }
}