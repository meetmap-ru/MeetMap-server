package ru.devgroup.adventuremap.data.entity

import ru.devgroup.adventuremap.data.model.DatabaseEntity

data class MessageEntity(
    val sender: Long,
    val replyTo: Long?,
    val timestamp: Long,
    val text: String,
    val media: List<Long>,
    val watchedBy: List<Long>,
    val eventId: Long,
    val edited: Boolean,
) : DatabaseEntity {
    val id: Long = 0
}
