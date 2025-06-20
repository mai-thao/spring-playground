package com.example.petadoptionapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke // Must explicitly import this "invoke" fxn to enable the Kotlin DSL

// Reference docs at https://spring.io/guides/gs/securing-web and https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html
@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize(HttpMethod.GET, "/pets/**", permitAll) // Allow all GET requests
                authorize(HttpMethod.POST, "/pets/**", authenticated) // Force authentication for POST, PUT, DELETE requests
                authorize(HttpMethod.PUT, "/pets/**", authenticated)
                authorize(HttpMethod.DELETE, "/pets/**", authenticated)
                authorize(anyRequest, denyAll) // Block all other requests
            }
            httpBasic { } // Enable basic HTTP authentication
            csrf { disable() } // Disable csrf since this is a stateless API, not a browser app that uses session & cookies
        }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user")
            .password("{noop}password") // {noop} is an identifier to turn off encoding and use it as plaintext (for testing)
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
