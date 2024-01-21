package ru.devgroup.adventuremap.domain.model.city

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Domain

data class City(
    val id: Long,
    val name: String,
    val district: String,
    val country: String
) : Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}