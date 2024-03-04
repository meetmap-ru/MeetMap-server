package ru.devgroup.adventuremap.domain.response

data class Token(
    val access_token: String,
    val refresh_token: String,
)
