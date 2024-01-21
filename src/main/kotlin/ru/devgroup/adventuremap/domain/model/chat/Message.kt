package ru.devgroup.adventuremap.domain.model.chat

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Domain
import ru.devgroup.adventuremap.domain.model.media.Media

data class Message(
    val sender: Long,
    val replyTo: Long?,
    val timestamp: Long,
    val text: String,
    val media: List<Media>,
    val forwarded: Long?,
    val watchedBy: List<Long>,
    val chatId: Long,
    val edited: Boolean
) : Domain {
    val id: Long = 0

    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}