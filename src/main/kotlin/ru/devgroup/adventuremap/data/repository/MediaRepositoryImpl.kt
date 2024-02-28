package ru.devgroup.adventuremap.data.repository

import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.MediaDao
import ru.devgroup.adventuremap.data.util.MediaDomainConverter
import ru.devgroup.adventuremap.domain.model.media.Media
import ru.devgroup.adventuremap.domain.repository.MediaRepository
import ru.devgroup.adventuremap.domain.util.MediaUtils

@Component
class MediaRepositoryImpl(
    private val mediaDao: MediaDao,
    private val mediaUtils: MediaUtils,
) : MediaRepository, MediaDomainConverter() {
    override fun load(vararg multipart: MultipartFile): State<List<Media>> {
        TODO("Not yet implemented")
    }

    override fun upload(id: Long): State<Resource> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): State<Unit> {
        mediaDao.deleteById(id)
        return State.Success(Unit)
    }
}
