package ru.devgroup.adventuremap.domain.usecase

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.domain.repository.CategoryRepository

@Component
class GetCategoryByIdUseCase(
    private val categoryRepository: CategoryRepository,
) {
    operator fun invoke(id: Long) = categoryRepository.getById(id)
}
