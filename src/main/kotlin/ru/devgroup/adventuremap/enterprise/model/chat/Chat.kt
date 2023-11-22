package ru.devgroup.adventuremap.enterprise.model.chat

import ru.devgroup.adventuremap.enterprise.model.event.Event
import ru.devgroup.adventuremap.enterprise.model.media.Media

data class Chat(
    val id: Long,
    val media: List<Media>,
    val event: Event // можно поменять на eventId пока хз
)