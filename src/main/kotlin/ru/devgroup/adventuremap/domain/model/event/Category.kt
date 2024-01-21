package ru.devgroup.adventuremap.domain.model.event

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Domain

data class Category(
    val id: Long,
    val title: String,
    val ageLimit: Int
) : Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}