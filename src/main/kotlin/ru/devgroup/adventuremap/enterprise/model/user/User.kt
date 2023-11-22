package ru.devgroup.adventuremap.enterprise.model.user

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.enterprise.model.city.City
import ru.devgroup.adventuremap.enterprise.model.Domain
import ru.devgroup.adventuremap.enterprise.model.event.Event
import ru.devgroup.adventuremap.enterprise.model.media.Media

data class User(
    val id: Long,
    val name: String,
    val lastName: String,
    val nickname: String,
    val email: String,
    val phoneNumber: String,
    val avatars: List<Media>,
    val city: City,
    val birthDate: Long,
    val events: List<Event>,
    val history: List<Event>,
    val description: String,
    val password: String,
    val token: String,
    val subscribers: List<Long>
): Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}
