package ru.devgroup.adventuremap.domain.model.event

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Domain
import ru.devgroup.adventuremap.domain.model.chat.Chat
import ru.devgroup.adventuremap.domain.model.media.Media
import ru.devgroup.adventuremap.domain.model.user.User

data class Event(
    val title: String,
    val media: List<Media>,
    val description: String,
    val members: List<User>,
    val category: List<Category>,
    val ageLimit: Int,
    val date: Pair<Long, Long>,
    val cityId: Long,
    val location: String,
    val organizers: List<User>,
    val timestamp: Long,
    val chat: Chat, // можно поменять на chatId, пока хз
    val maxPersons: Int,
    val price: Int
) : Domain {
    val id: Long = 0

    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}