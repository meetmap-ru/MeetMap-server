package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.MediaDao
import ru.devgroup.adventuremap.data.util.MediaDomainConverter
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.model.media.Media
import ru.devgroup.adventuremap.domain.repository.MediaRepository
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Component
class MediaRepositoryImpl(
    private val mediaDao: MediaDao,
) : MediaRepository, MediaDomainConverter() {
    override fun getById(id: Long): State<Media> {
        val mediaEntity = mediaDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(mediaEntity.asDomain())
    }

    override fun delete(id: Long): State<Unit> {
        mediaDao.deleteById(id)
        return State.Success(Unit)
    }

    override fun create(media: Media): State<Media> {
        val media = mediaDao.save(media.asDatabaseEntity())
        return State.Success(media.asDomain())
    }

    override fun getByIds(vararg ids: Long): State<List<Media>> {
        val media: MutableList<Media> = mutableListOf()
        ids.forEach { id ->
            mediaDao.findById(id).getOrNull()?.let {
                media.add(it.asDomain())
            }
        }
        return State.Success(media)
    }
}
