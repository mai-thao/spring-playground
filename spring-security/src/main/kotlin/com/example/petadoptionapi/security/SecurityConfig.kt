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

/**
 * A super simple basic authentication configuration with hardcoded in-memory user credentials.
 * Pair this security config with the "ControllerSimpleAuth" class.
 *
 * Restrictions:
 * - ALl `GET` requests to the `/pets` URI have no restrictions
 * - All `POST`, `PUT` requests to the `/pets` URI are accessible only if logged in (e.g. USER, MANAGER, or ADMIN)
 * - ALL `DELETE` requests to the `/pets` URI are accessible only by a MANAGER user
 * -- There is a granular method-level security where the `DELETE` all method is restricted to only ADMINs (see the @PreAuthorize annotation)
 *
 * Reference doc: https://spring.io/guides/gs/securing-web and https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html
 */
@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize(HttpMethod.GET, "/pets/**", permitAll)
                authorize(HttpMethod.POST, "/pets/**", authenticated)
                authorize(HttpMethod.PUT, "/pets/**", authenticated)
                authorize(HttpMethod.DELETE, "/pets/**", hasAnyRole("MANAGER", "ADMIN"))
                authorize(anyRequest, denyAll)
            }
            httpBasic { } // Enable basic HTTP authentication
            csrf { disable() } // Disable csrf since this is a stateless API, not a browser app that uses session & cookies
        }
        return http.build()
    }

    // Hardcoded, in-memory login credentials (MEANT FOR TESTING ONLY!)
    // In real production code, we would be more sophisticated and use a 3rd party user store solution!
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user")
            .password("{noop}password") // {noop} is an identifier to turn off encoding and use it as plaintext (for testing)
            .roles("USER")
            .build()

        val manager = User.withUsername("manager")
            .password("{noop}managerpassword")
            .roles("MANAGER")
            .build()

        val admin = User.withUsername("admin")
            .password("{noop}adminpassword")
            .roles("MANAGER", "ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, manager, admin)
    }
}
