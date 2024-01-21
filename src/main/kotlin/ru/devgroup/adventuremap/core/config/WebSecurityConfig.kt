package ru.devgroup.adventuremap.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.core.jwt.AuthTokenFilter
import ru.devgroup.adventuremap.core.jwt.AuthEntryPoint


@EnableWebSecurity
@Configuration
@Component
class WebSecurityConfig {

    @Bean
    fun provideAuthEntryPoint(): AuthenticationEntryPoint = AuthEntryPoint()

    @Bean
    fun provideAuthTokenFilter(): AuthTokenFilter = AuthTokenFilter()

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.getAuthenticationManager()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .formLogin {
                it.disable()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "api/user/post/sign-up",
                    "api/user/get/sign-in"
                ).permitAll()
                it.anyRequest().authenticated()

            }
            .exceptionHandling { exception ->
                exception.authenticationEntryPoint(
                    provideAuthEntryPoint()
                )
            }
            .addFilterAfter(provideAuthTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }


}