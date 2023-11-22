package ru.devgroup.adventuremap.enterprise.model.city

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.enterprise.model.Domain

data class City(
    val id: Long,
    val name: String,
    val district: String,
    val country: String
): Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}