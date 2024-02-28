package ru.devgroup.adventuremap.domain.usecase

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.domain.repository.MediaRepository

@Component
class GetMediaByIdUseCase(
    private val mediaRepository: MediaRepository,
) {
    operator fun invoke(id: Long) = mediaRepository.upload(id)
}
