package ru.devgroup.adventuremap.domain.model.media

import ru.devgroup.adventuremap.domain.model.Domain

data class Media(
    val name: String,
    val link: String,
    val type: String,
) : Domain {
    val id: Long = 0
}
