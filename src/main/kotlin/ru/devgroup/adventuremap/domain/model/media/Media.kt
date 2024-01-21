package ru.devgroup.adventuremap.domain.model.media

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Domain

data class Media(
    val name: String,
    val link: String,
    val type: String
) : Domain {
    val id: Long = 0

    override fun asDatabaseEntity(): DatabaseEntity {
        TODO("Not yet implemented")
    }
}