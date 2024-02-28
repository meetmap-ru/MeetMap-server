package ru.devgroup.adventuremap.domain.repository

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.media.Media

interface MediaRepository {
    fun load(vararg multipart: MultipartFile): State<List<Media>>

    fun upload(id: Long): State<Resource>

    fun delete(id: Long): State<Unit>
}
