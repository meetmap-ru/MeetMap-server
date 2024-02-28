package ru.devgroup.adventuremap.domain.request.message

data class EditMessageRequest(
    val id: Long,
    val media: List<Long>,
    val replyTo: Long?,
    val text: String,
)
