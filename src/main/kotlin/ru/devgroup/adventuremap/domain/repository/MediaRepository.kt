package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.media.Media

interface MediaRepository {
    fun getById(id: Long): State<Media>

    fun delete(id: Long): State<Unit>

    fun create(media: Media): State<Media>

    fun getByIds(vararg ids: Long): State<List<Media>>
}
