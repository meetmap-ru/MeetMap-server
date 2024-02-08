package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Repository
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.MessageDao
import ru.devgroup.adventuremap.data.util.MessageDomainConverter
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.model.message.Message
import ru.devgroup.adventuremap.domain.repository.MessageRepository
import kotlin.jvm.optionals.getOrElse

@Repository
class MessageRepositoryImpl(
    private val messageDao: MessageDao,
) : MessageRepository, MessageDomainConverter() {
    override fun createMessage(message: Message): State<Message> {
        return State.Success(messageDao.save(message.asDatabaseEntity()).asDomain())
    }

    override fun deleteMessage(
        messageId: Long,
        userId: Long,
    ): State<Unit> {
        val entity = messageDao.findById(messageId).getOrElse { throw NotFoundException() }
        messageDao.delete(entity)
        return State.Success(Unit)
    }

    override fun editMessage(
        messageId: Long,
        media: List<Long>,
        replyTo: Long?,
        text: String,
    ): State<Message> {
        val entity = messageDao.findById(messageId).getOrElse { throw NotFoundException() }
        return State.Success(
            messageDao.save(
                entity.copy(
                    media = media,
                    replyTo = replyTo,
                    text = text,
                ),
            ).asDomain(),
        )
    }

    override fun watchMessage(
        messageId: Long,
        userId: Long,
    ): State<Unit> {
        val entity = messageDao.findById(messageId).getOrElse { throw NotFoundException() }
        messageDao.save(entity.copy(watchedBy = entity.watchedBy + userId))
        return State.Success(Unit)
    }
}
