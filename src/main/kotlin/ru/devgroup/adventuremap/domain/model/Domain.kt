package ru.devgroup.adventuremap.domain.model

import ru.devgroup.adventuremap.data.model.DatabaseEntity

interface Domain {
    fun asDatabaseEntity(): DatabaseEntity
}