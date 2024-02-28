package ru.devgroup.adventuremap.data.entity

import jakarta.persistence.*
import ru.devgroup.adventuremap.data.model.DatabaseEntity

@Table
@Entity
data class MediaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    val name: String,
    val type: String,
    val data: ByteArray,
) : DatabaseEntity
