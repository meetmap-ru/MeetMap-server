package ru.devgroup.adventuremap.data.repository

import org.springframework.data.domain.Example
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.EventDao
import ru.devgroup.adventuremap.data.dao.MessageDao
import ru.devgroup.adventuremap.data.entity.EventEntity
import ru.devgroup.adventuremap.data.entity.MessageEntity
import ru.devgroup.adventuremap.data.util.MessageDomainConverter
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.exceptions.PermissionDenied
import ru.devgroup.adventuremap.domain.model.Permission
import ru.devgroup.adventuremap.domain.model.message.Message
import ru.devgroup.adventuremap.domain.repository.MessageRepository
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Repository
class MessageRepositoryImpl(
    private val messageDao: MessageDao,
    private val eventsDao: EventDao,
) : MessageRepository, MessageDomainConverter() {
    override fun createMessage(message: Message): State<Message> {
        return State.Success(messageDao.save(message.asDatabaseEntity()).asDomain())
    }

    override fun deleteMessage(
        userId: Long,
        vararg messageId: Long,
    ): State<Unit> {
        messageId
            .onEach { id ->
                messageDao
                    .findById(id)
                    .getOrNull()
                    ?.let { message ->
                        if (message.sender == userId) messageDao.delete(message)
                    }
            }
        return State.Success(Unit)
    }

    override fun editMessage(
        userId: Long,
        messageId: Long,
        media: List<Long>,
        replyTo: Long?,
        text: String,
    ): State<Message> {
        val entity =
            messageDao
                .findById(messageId)
                .getOrElse { throw NotFoundException() }
                .takeIf { message -> message.sender == userId }
                ?: throw PermissionDenied()
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
        val entity =
            messageDao
                .findById(messageId)
                .getOrElse { throw NotFoundException() }
                .takeIf {
                    val event =
                        eventsDao
                            .findById(it.eventId ?: -1)
                            .getOrElse { throw NotFoundException() }
                    event.isActionPermitted(userId, event.chatVisibility)
                } ?: throw PermissionDenied()

        messageDao.save(entity.copy(watchedBy = (entity.watchedBy ?: emptyList()) + userId))
        return State.Success(Unit)
    }

    override fun getMessageById(
        userId: Long,
        vararg ids: Long,
    ): State<List<Message>> {
        val messages =
            ids
                .toList()
                .mapNotNull { id -> messageDao.findById(id).getOrNull() }
                .filter { mes ->
                    val event =
                        eventsDao.findById(mes.eventId ?: -1)
                            .getOrElse { throw NotFoundException("message do not attached to any event or event deleted") }
                    event
                        .isActionPermitted(userId, event.chatVisibility)
                }
                .map { it.asDomain() }

        return State.Success(messages)
    }

    override fun getMessageByEvent(
        userId: Long,
        limit: Int,
        page: Int,
        eventId: Long,
    ): State<List<Message>> {
        eventsDao.findById(eventId)
            .getOrElse { throw NotFoundException("event with id = $eventId not found") }
            .takeIf { event -> event.isActionPermitted(userId, event.chatVisibility) }
            ?: throw PermissionDenied()

        val messages =
            messageDao.findAll(
                Example.of(MessageEntity(eventId = eventId)),
                Pageable.ofSize(limit).withPage(page),
            ).content.mapNotNull { it.asDomain() }

        return State.Success(messages)
    }

    private fun EventEntity.isActionPermitted(
        userId: Long,
        action: Permission,
    ): Boolean {
        if (userId == owner) return true
        if (userId in organizers && action != Permission.OnlyOwner) return true
        if (userId in members && action == Permission.OnlyMembers) return true
        if (action == Permission.AllUsers) return true
        return false
    }
}
