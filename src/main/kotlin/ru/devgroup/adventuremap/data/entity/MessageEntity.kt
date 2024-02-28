package ru.devgroup.adventuremap.data.entity

import jakarta.persistence.*
import ru.devgroup.adventuremap.data.model.DatabaseEntity

@Entity
@Table
data class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val sender: Long? = null,
    val replyTo: Long? = null,
    val timestamp: Long? = null,
    val text: String? = null,
    val media: List<Long>? = null,
    val watchedBy: List<Long>? = null,
    val eventId: Long? = null,
    val edited: Boolean? = null,
) : DatabaseEntity
