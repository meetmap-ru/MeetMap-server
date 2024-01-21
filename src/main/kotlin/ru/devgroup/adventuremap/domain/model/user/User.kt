package ru.devgroup.adventuremap.domain.model.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.devgroup.adventuremap.data.entity.UserEntity
import ru.devgroup.adventuremap.domain.model.Domain

data class User(
    val id: Long = -1,
    val name: String? = null,
    val lastName: String? = null,

    @JvmSynthetic
    val nickname: String? = null,
    val email: String? = null,
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

    val role: MutableList<Role> = mutableListOf(Role.USER)
) : Domain, UserDetails {

    override fun asDatabaseEntity(): UserEntity = UserEntity(
        name,
        lastName,
        nickname,
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

    fun commonInfo(): User = User(
        id,
        name,
        lastName,
        nickname,
        null,
        null,
        city,
        birthDate,
        description,
        verified,
        registrationTimestamp,
        lastSeen,
        avatars,
        subscribers,
        categories
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        role.map {SimpleGrantedAuthority(it.name) }.toMutableList()

    override fun getPassword(): String? = null

    override fun getUsername(): String? = nickname

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = true
}
