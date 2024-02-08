package ru.devgroup.adventuremap.data.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Permission

@Entity
@Table
data class EventEntity(
    val title: String,
    val media: List<Long>,
    val description: String,
    val members: List<Long>,
    val category: List<Long>,
    val ageLimit: Int,
    val date: Pair<Long, Long>,
    val cityId: Long,
    val address: String,
    val coordinates: Pair<Long, Long>,
    val owner: Long,
    val organizers: List<Long>,
    val timestamp: Long,
    val maxPersons: Int,
    val price: Float,
    val mediaVisibility: Permission = Permission.AllUsers,
    val mediaChangePermission: Permission = Permission.OnlyOrganizers,
    val chatVisibility: Permission = Permission.OnlyMembers,
    val membersVisibility: Permission = Permission.OnlyMembers,
    val memberChangePermission: Permission = Permission.OnlyOwner,
) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
