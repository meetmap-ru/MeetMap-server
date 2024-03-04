package ru.devgroup.adventuremap.data.util

import ru.devgroup.adventuremap.data.entity.UserEntity
import ru.devgroup.adventuremap.domain.model.user.User
import ru.devgroup.adventuremap.domain.util.DomainConverter

open class UserDomainConverter : DomainConverter<UserEntity, User> {
    override fun User.asDatabaseEntity(): UserEntity =
        UserEntity(
            id,
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
            role,
        )

    override fun UserEntity.asDomain(): User =
        User(
            id,
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
            role,
        )
}
