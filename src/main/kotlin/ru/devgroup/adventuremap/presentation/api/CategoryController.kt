package ru.devgroup.adventuremap.presentation.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.devgroup.adventuremap.core.config.CATEGORY_API
import ru.devgroup.adventuremap.domain.repository.CategoryRepository
import ru.devgroup.adventuremap.domain.request.category.ListCategoriesRequest

@RestController
@RequestMapping(CATEGORY_API)
class CategoryController(
    private val categoryRepository: CategoryRepository,
) {
    @GetMapping("/get")
    fun getCategoriesById(
        @RequestBody body: ListCategoriesRequest,
    ): ResponseEntity<Any> = categoryRepository.getByIds(*body.id.toLongArray()).asResponse()
}
