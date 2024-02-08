package ru.devgroup.adventuremap.domain.request.event

data class GetEventRequest(
    val id: Long? = null,
    val cityId: Long? = null,
    val keyWord: String? = null,
    val categories: List<Long>,
)
