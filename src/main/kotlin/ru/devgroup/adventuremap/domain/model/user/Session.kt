package ru.devgroup.adventuremap.domain.model.user

data class Session(
    val id: Long,
    val device: String,
    val creationDate: Long,
    val userId: Long
)