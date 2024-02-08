package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.entity.Category

interface CategoryRepository {
    fun getById(id: Long): State<Category>

    fun getByIds(vararg ids: Long): State<List<Category>>
}
