package ru.devgroup.adventuremap.data.model

import ru.devgroup.adventuremap.enterprise.model.Domain

interface DatabaseEntity {
    fun asDomain(): Domain
}