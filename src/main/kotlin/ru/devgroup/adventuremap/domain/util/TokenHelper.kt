package ru.devgroup.adventuremap.domain.util

import io.jsonwebtoken.Claims
import org.springframework.http.ResponseEntity
import ru.devgroup.adventuremap.domain.model.user.User


interface TokenHelper {

    fun isTokenValid(token: String?): Boolean

    fun createToken(userDetails: User, claims: HashMap<String, Any?> = HashMap()): String

    fun getClaims(token: String?): Claims?

}