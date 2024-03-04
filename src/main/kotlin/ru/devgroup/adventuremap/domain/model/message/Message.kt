package ru.devgroup.adventuremap.domain.model.message

import ru.devgroup.adventuremap.domain.model.Domain
import ru.devgroup.adventuremap.domain.model.media.Media

data class Message(
    val id: Long = 0,
    val sender: Long,
    val replyTo: Long?,
    val timestamp: Long,
    val text: String,
    val media: List<Media>,
    val watchedBy: List<Long>,
    val eventId: Long,
    val edited: Boolean,
) : Domain
