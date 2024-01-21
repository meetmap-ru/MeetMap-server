package ru.devgroup.adventuremap.domain.request.user

import ru.devgroup.adventuremap.domain.model.user.User

data class SignUpRequest(
    val username: String,
    val name: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String
) {
    fun asDomain(): User = User(
        0,
        name,
        lastName,
        username,
        email,
        phoneNumber
    )
}