package ru.devgroup.adventuremap.domain.model.event

import ru.devgroup.adventuremap.domain.model.Domain
import ru.devgroup.adventuremap.domain.model.Permission
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
    val address: String,
    val organizers: List<User>,
    val timestamp: Long,
    val chat: Long,
    val maxPersons: Int,
    val price: Float,
    val mediaVisibility: Permission = Permission.AllUsers,
    val chatVisibility: Permission = Permission.OnlyMembers,
    val membersVisibility: Permission = Permission.OnlyMembers,
) : Domain {
    val id: Long = 0

    companion object {
        fun new(
            title: String,
            media: List<Media> = listOf(),
            description: String = "",
            members: List<User> = listOf(),
            category: List<Category> = listOf(),
            ageLimit: Int,
            date: Pair<Long, Long>,
            cityId: Long,
            address: String,
            organizers: List<User>,
            timestamp: Long,
            chat: Long,
            maxPersons: Int,
            price: Float,
            mediaVisibility: Permission = Permission.AllUsers,
            chatVisibility: Permission = Permission.OnlyMembers,
            membersVisibility: Permission = Permission.OnlyMembers,
        ) = Event(
            title,
            media,
            description,
            members,
            category,
            ageLimit,
            date,
            cityId,
            address,
            organizers,
            timestamp,
            chat,
            maxPersons,
            price,
            mediaVisibility,
            chatVisibility,
            membersVisibility,
        )
    }
}
