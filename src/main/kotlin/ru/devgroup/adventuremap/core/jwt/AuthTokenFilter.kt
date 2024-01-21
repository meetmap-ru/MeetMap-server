package ru.devgroup.adventuremap.core.jwt


import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.repository.UserRepositoryImpl
import ru.devgroup.adventuremap.domain.model.user.User
import ru.devgroup.adventuremap.domain.repository.UserRepository
import java.io.IOException

@Component
class AuthTokenFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var userRepository: UserRepository


    @Autowired
    private lateinit var jwtHelper: JwtHelper

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = parseJwt(request)
            if (jwtHelper.isTokenValid(token)) {
                val username: String = jwtHelper.getClaims(token)?.subject ?: throw Exception("subject is null")
                when(val state = userRepository.getByUsername(username)) {
                    is State.Success -> {
                        val user = state.data
                        val authentication = UsernamePasswordAuthenticationToken(
                            user, null, user.authorities
                        )
                        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                    else -> {
                        throw Exception("user does not exist")
                    }
                }
            }
        } catch (e: Exception) {
            Companion.logger.error("Cannot set user authentication: {}", e)
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String = request
        .getHeader("Authorization")
        .split("Bearer ")[1]

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)
    }
}