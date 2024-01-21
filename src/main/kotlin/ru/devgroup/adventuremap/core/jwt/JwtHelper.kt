package ru.devgroup.adventuremap.core.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.domain.model.user.User
import ru.devgroup.adventuremap.domain.util.TokenHelper
import java.security.Key
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtHelper(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expirationTime}") private val expirationTime: Long
) : TokenHelper {

    private var jwtSecretKey: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

    override fun isTokenValid(token: String?): Boolean {
        runCatching {
            Jwts.parser()
                .verifyWith(jwtSecretKey as SecretKey)
                .build()
                .parseSignedClaims(token?.prepareToken())
            return true
        }
        return false
    }

    override fun createToken(
        userDetails: User,
        claims: HashMap<String, Any?>
    ): String {
        val token = Jwts.builder()
            .claims(
                claims + hashMapOf(
                    "id" to userDetails.id,
                    "role" to userDetails.role,
                    "regTime" to (userDetails.registrationTimestamp?:0)
                )
            )
            .subject(userDetails.username)
            .issuedAt(Date())
            .expiration(Date(Date().time + expirationTime))
            .signWith(jwtSecretKey).compact()
        return "Bearer $token"
    }

    override fun getClaims(token: String?): Claims? {
        return runCatching {
            Jwts.parser()
                .verifyWith(jwtSecretKey as SecretKey)
                .build()
                .parseSignedClaims(token?.prepareToken())
                .payload
        }.getOrNull()
    }

    private fun String.prepareToken(): String = this.split(" ").lastOrNull()?:this
}