package ru.devgroup.adventuremap.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
    val title: String,
    val ageLimit: Int,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
