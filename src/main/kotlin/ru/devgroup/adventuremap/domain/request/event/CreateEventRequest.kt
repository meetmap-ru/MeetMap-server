package ru.devgroup.adventuremap.domain.request.event

import ru.devgroup.adventuremap.data.entity.Category
import ru.devgroup.adventuremap.domain.model.Permission

class CreateEventRequest(
    val title: String,
    val description: String,
    val category: List<Category>,
    val ageLimit: Int,
    val date: Pair<Long, Long>,
    val cityId: Long,
    val address: String,
    val coordinates: Pair<Long, Long>,
    val maxPersons: Int,
    val price: Float,
    val mediaVisibility: Permission = Permission.AllUsers,
    val mediaChangePermission: Permission = Permission.OnlyOrganizers,
    val chatVisibility: Permission = Permission.OnlyMembers,
    val membersVisibility: Permission = Permission.OnlyMembers,
    val memberChangePermission: Permission = Permission.OnlyOwner,
)
