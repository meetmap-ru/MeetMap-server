package ru.devgroup.adventuremap.domain.request.event

data class ChangeEventItemIdRequest(
    val itemId: Long,
    val eventId: Long,
)
