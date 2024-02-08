package ru.devgroup.adventuremap.domain.model.event

import ru.devgroup.adventuremap.data.entity.Category
import ru.devgroup.adventuremap.domain.model.Domain
import ru.devgroup.adventuremap.domain.model.Permission
import ru.devgroup.adventuremap.domain.model.media.Media

data class Event(
    val title: String,
    val media: List<Media>,
    val description: String,
    val members: List<Long>,
    val category: List<Category>,
    val ageLimit: Int,
    val date: Pair<Long, Long>,
    val cityId: Long,
    val address: String,
    val coordinates: Pair<Long, Long>,
    val owner: Long,
    val organizers: List<Long>,
    val timestamp: Long,
    val chat: Long,
    val maxPersons: Int,
    val price: Float,
    val mediaVisibility: Permission = Permission.AllUsers,
    val mediaChangePermission: Permission = Permission.OnlyOrganizers,
    val chatVisibility: Permission = Permission.OnlyMembers,
    val membersVisibility: Permission = Permission.OnlyMembers,
    val memberChangePermission: Permission = Permission.OnlyOwner,
) : Domain {
    val id: Long = 0

    companion object {
        fun new(
            title: String,
            media: List<Media> = listOf(),
            description: String = "",
            members: List<Long> = listOf(),
            category: List<Category> = listOf(),
            ageLimit: Int,
            date: Pair<Long, Long>,
            cityId: Long,
            address: String,
            coordinates: Pair<Long, Long>,
            owner: Long,
            organizers: List<Long>,
            timestamp: Long,
            chat: Long,
            maxPersons: Int,
            price: Float,
            mediaVisibility: Permission = Permission.AllUsers,
            mediaChangePermission: Permission = Permission.OnlyOrganizers,
            chatVisibility: Permission = Permission.OnlyMembers,
            membersVisibility: Permission = Permission.OnlyMembers,
            memberChangePermission: Permission = Permission.OnlyOwner,
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
            coordinates,
            owner,
            organizers,
            timestamp,
            chat,
            maxPersons,
            price,
            mediaVisibility,
            mediaChangePermission,
            chatVisibility,
            membersVisibility,
            memberChangePermission,
        )
    }
}
