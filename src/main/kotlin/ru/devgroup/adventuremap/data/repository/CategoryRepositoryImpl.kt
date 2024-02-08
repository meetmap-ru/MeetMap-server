package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.CategoryDao
import ru.devgroup.adventuremap.data.entity.Category
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.repository.CategoryRepository
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Component
class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao,
) : CategoryRepository {
    override fun getById(id: Long): State<Category> {
        val category = categoryDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(category)
    }

    override fun getByIds(vararg ids: Long): State<List<Category>> {
        val category: MutableList<Category> = mutableListOf()
        ids.forEach { id ->
            categoryDao.findById(id).getOrNull()?.let {
                category.add(it)
            }
        }
        return State.Success(category)
    }
}
