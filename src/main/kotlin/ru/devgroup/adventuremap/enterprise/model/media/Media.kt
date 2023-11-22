package ru.devgroup.adventuremap.enterprise.model.media

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.enterprise.model.Domain

data class Media(
    val id: Long,
    val name: String,
    val link: String,
    val type: String
): Domain {
    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}