package ru.devgroup.adventuremap.domain.util

import ru.devgroup.adventuremap.core.util.State
import kotlin.math.min

/**
 * PaginationController управляет количеством прислываемых данных
 * Содержит функцию page(limit: Int, offset: Int)
 * limit - максимальное кол-во элементов
 * offset - смещение от начала, равное индексу первого элемента, который будет включен в ответ
 */
interface PaginationController {
    fun <T> State<List<T>>.page(
        limit: Int,
        offset: Int,
    ): State<List<T>> {
        var data = this.data ?: emptyList()
        if (data.size > offset) {
            data = data.subList(offset, min(limit + offset, data.lastIndex))
            return when (this) {
                is State.Success -> State.Success(data, this.message, this.status)
                is State.Error -> State.Error(data, this.message, this.status)
                else -> return this
            }
        }
        return this
    }
}
