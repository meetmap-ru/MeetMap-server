package ru.devgroup.adventuremap.data.util

import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.core.util.fileContentType
import ru.devgroup.adventuremap.data.dao.MediaDao
import ru.devgroup.adventuremap.data.entity.MediaEntity
import ru.devgroup.adventuremap.domain.exceptions.ServerError
import ru.devgroup.adventuremap.domain.model.media.Media
import ru.devgroup.adventuremap.domain.repository.MediaRepository
import ru.devgroup.adventuremap.domain.util.MediaUtils
import java.util.*

@Service
class MediaRepositoryImpl(
    private val mediaUtils: MediaUtils,
    private val mediaDao: MediaDao,
) : MediaRepository, MediaDomainConverter() {
    @OptIn(ExperimentalStdlibApi::class)
    override fun load(vararg multipart: MultipartFile): State<List<Media>> {
        val entity =
            multipart.map { file ->
                mediaDao.save(
                    MediaEntity(
                        name = "${UUID.randomUUID()}${
                            System.currentTimeMillis().toHexString()
                        }.${fileContentType[file.contentType]}",
                        type = fileContentType[file.contentType] ?: throw ServerError("Cannot find content type"),
                        data = mediaUtils.compressMedia(file.bytes),
                    ),
                ).asDomain()
            }

        return State.Success(entity)
    }

    override fun upload(id: Long): State<Resource> {
        TODO()
    }

    override fun delete(id: Long): State<Unit> {
        TODO()
    }

    override fun getById(id: Long): State<Media> {
        TODO("Not yet implemented")
    }
}
