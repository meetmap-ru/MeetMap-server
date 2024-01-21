package ru.devgroup.adventuremap.data.model

import ru.devgroup.adventuremap.domain.model.Domain

interface DatabaseEntity {
    fun asDomain(): Domain
}