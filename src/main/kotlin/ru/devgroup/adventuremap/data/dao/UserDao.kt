package ru.devgroup.adventuremap.data.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.devgroup.adventuremap.data.entity.UserEntity
import java.util.*

interface UserDao : JpaRepository<UserEntity, Long> {
    override fun findById(id: Long): Optional<UserEntity>

    fun findByPhoneNumber(phoneNumber: String): UserEntity?

    fun findByEmail(email: String): UserEntity?

    fun findByUsername(username: String): UserEntity?

    fun findByCity(city: Long): List<UserEntity>

    @Query("select user from UserEntity user where user.username = ?1 or user.phoneNumber = ?1 or user.email = ?1")
    fun findByLogin(login: String): UserEntity?

    @Query("select user from UserEntity user where user.name like %:part% or user.lastName like %:part%")
    fun findByName(
        @Param("part") name: String,
    ): List<UserEntity>
}
