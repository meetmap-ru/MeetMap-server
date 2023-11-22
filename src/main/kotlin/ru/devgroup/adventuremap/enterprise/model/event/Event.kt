package ru.devgroup.adventuremap.enterprise.model.event

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.enterprise.model.Domain
import ru.devgroup.adventuremap.enterprise.model.chat.Chat
import ru.devgroup.adventuremap.enterprise.model.media.Media
import ru.devgroup.adventuremap.enterprise.model.user.User

data class Event(
    val id: Long,
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
): Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}