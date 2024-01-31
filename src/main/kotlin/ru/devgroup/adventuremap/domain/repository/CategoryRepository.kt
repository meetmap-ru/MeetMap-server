package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.event.Category

interface CategoryRepository {
    fun getById(id: Long): State<Category>
}
