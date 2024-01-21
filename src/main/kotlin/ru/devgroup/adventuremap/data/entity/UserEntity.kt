package ru.devgroup.adventuremap.data.entity

import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails
import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.user.Role
import ru.devgroup.adventuremap.domain.model.user.User

@Entity
@Table
data class UserEntity(
    val name: String? = null,
    val lastName: String? = null,

    @Column(unique = true)
    val username: String? = null,
    @Column(unique = true)
    val email: String? = null,
    @Column(unique = true)
    val phoneNumber: String? = null,

    val city: Long? = null,
    val birthDate: Long? = null,
    val description: String? = null,
    val verified: Boolean? = false,
    val registrationTimestamp: Long? = null,

    val lastSeen: Long? = null,
    val avatars: List<Long>? = null,
    val subscribers: List<Long>? = null,
    val categories: List<Long>? = null,

    val events: List<Long>? = null,
    val history: List<Long>? = null,

    val role: MutableList<Role> = mutableListOf(Role.USER),

    val password: String? = null
) : DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    override fun asDomain(): User = User(
        id,
        name,
        lastName,
        username,
        email,
        phoneNumber,
        city,
        birthDate,
        description,
        verified,
        registrationTimestamp,
        lastSeen,
        avatars,
        subscribers,
        categories,
        events,
        history,
        role
    )
}