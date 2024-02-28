package ru.devgroup.adventuremap.domain.request.event

data class GetFilteredEventRequest(
    val cityId: Long? = null,
    val keyWord: String? = null,
    val categories: List<Long>,
)
