package ru.devgroup.adventuremap.domain.request.user

data class SignInRequest(
    val username: String,
    val password: String,
)