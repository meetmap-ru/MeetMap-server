package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.media.Media

interface MediaRepository {
    fun getById(id: Long): State<Media>
}
