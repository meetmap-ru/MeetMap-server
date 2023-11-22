package ru.devgroup.adventuremap.enterprise.model

import ru.devgroup.adventuremap.data.model.DatabaseEntity

interface Domain {
    fun asDatabaseEntity(): DatabaseEntity
}