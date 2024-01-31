package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.media.Media
import ru.devgroup.adventuremap.domain.repository.MediaRepository

@Component
class MediaRepositoryImpl : MediaRepository {
    override fun getById(id: Long): State<Media> {
        println("auauauauaua")
        return State.Success(Media("", "cum", "cock"))
    }
}
