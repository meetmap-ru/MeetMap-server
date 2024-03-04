package ru.devgroup.adventuremap.data.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.devgroup.adventuremap.data.entity.EventEntity
import ru.devgroup.adventuremap.data.entity.UserEntity
import java.util.*

interface EventDao : JpaRepository<EventEntity, Long> {
    override fun findById(id: Long): Optional<EventEntity>

    fun findByCityId(city: Long): List<EventEntity>

    @Query("select user from UserEntity user where user.nickname = ?1 or user.phoneNumber = ?1 or user.email = ?1")
    fun findByLogin(login: String): UserEntity?

    @Query(
        "select event from EventEntity event where " +
            "event.title like %:keyword% or " +
            "event.description like %:keyword% or " +
            "event.address like %:keyword%",
    )
    fun findByKeyWord(
        @Param("keyword") keyword: String,
    ): List<EventEntity>
}
