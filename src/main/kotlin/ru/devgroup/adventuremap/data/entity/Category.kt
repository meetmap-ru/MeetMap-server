package ru.devgroup.adventuremap.data.entity

import jakarta.persistence.*

@Table
@Entity
data class Category(
    val title: String,
    val ageLimit: Int,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
