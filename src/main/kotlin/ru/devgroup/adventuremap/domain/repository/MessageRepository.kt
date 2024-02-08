package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.message.Message

interface MessageRepository {
    fun createMessage(message: Message): State<Message>

    fun deleteMessage(
        messageId: Long,
        userId: Long,
    ): State<Unit>

    fun editMessage(
        messageId: Long,
        media: List<Long>,
        replyTo: Long?,
        text: String,
    ): State<Message>

    fun watchMessage(
        messageId: Long,
        userId: Long,
    ): State<Unit>
}
