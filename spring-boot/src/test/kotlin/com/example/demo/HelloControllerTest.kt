package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HelloControllerTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun `should get all users`() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(content().string("Hello Mai from Kotlin Spring Boot!"))
    }
}
