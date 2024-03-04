package ru.devgroup.adventuremap.domain.util

import io.jsonwebtoken.Claims
import ru.devgroup.adventuremap.domain.model.user.User

interface TokenHelper {
    fun isTokenValid(token: String?): Boolean

    fun isRefreshToken(token: String): Boolean

    fun createToken(
        userDetails: User,
        claims: HashMap<String, Any?> = HashMap(),
        isAccessToken: Boolean = true,
    ): String

    fun getClaims(token: String?): Claims?
}
