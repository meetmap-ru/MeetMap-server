package ru.devgroup.adventuremap.domain.model.chat

import ru.devgroup.adventuremap.domain.model.Domain
import ru.devgroup.adventuremap.domain.model.event.Event
import ru.devgroup.adventuremap.domain.model.media.Media

data class Chat(
    val media: List<Media>,
    val event: Event, // можно поменять на eventId пока хз
) : Domain {
    val id: Long = 0
}
