package ru.devgroup.adventuremap.data.util

import org.springframework.beans.factory.annotation.Autowired
import ru.devgroup.adventuremap.data.entity.MessageEntity
import ru.devgroup.adventuremap.domain.model.message.Message
import ru.devgroup.adventuremap.domain.usecase.GetMediaByIdUseCase
import ru.devgroup.adventuremap.domain.util.DomainConverter

open class MessageDomainConverter : DomainConverter<MessageEntity, Message> {
    @Autowired
    private lateinit var getMediaByIdUseCase: GetMediaByIdUseCase

    override fun Message.asDatabaseEntity(): MessageEntity =
        MessageEntity(
            sender,
            replyTo,
            timestamp,
            text,
            media.map { it.id },
            watchedBy,
            eventId,
            edited,
        )

    override fun MessageEntity.asDomain(): Message =
        Message(
            sender,
            replyTo,
            timestamp,
            text,
            media.mapNotNull { getMediaByIdUseCase(it).data },
            watchedBy,
            eventId,
            edited,
        )
}