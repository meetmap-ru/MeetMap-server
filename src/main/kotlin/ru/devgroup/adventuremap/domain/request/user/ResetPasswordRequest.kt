package ru.devgroup.adventuremap.domain.request.user

data class ResetPasswordRequest(
    val oldPassword: String,
    val password: String
)