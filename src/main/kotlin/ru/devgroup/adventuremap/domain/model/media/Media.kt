package ru.devgroup.adventuremap.domain.model.media

import ru.devgroup.adventuremap.domain.model.Domain

data class Media(
    val id: Long = 0,
    val name: String,
    val link: String,
    val type: String,
) : Domain
