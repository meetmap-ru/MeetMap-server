package ru.devgroup.adventuremap.enterprise.model.chat

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.enterprise.model.Domain
import ru.devgroup.adventuremap.enterprise.model.media.Media

data class Message(
    val id: Long,
    val sender: Long,
    val replyTo: Long?,
    val timestamp: Long,
    val text: String,
    val media: List<Media>,
    val forwarded: Long?,
    val watchedBy: List<Long>,
    val chatId: Long,
    val edited: Boolean
): Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}