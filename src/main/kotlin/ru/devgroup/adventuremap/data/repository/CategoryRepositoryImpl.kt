package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.event.Category
import ru.devgroup.adventuremap.domain.repository.CategoryRepository

@Component
class CategoryRepositoryImpl : CategoryRepository {
    override fun getById(id: Long): State<Category> {
        TODO("Not yet implemented")
    }
}
