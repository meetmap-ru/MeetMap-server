package ru.devgroup.adventuremap.presentation.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.devgroup.adventuremap.core.config.MEDIA_API
import ru.devgroup.adventuremap.domain.exceptions.BaseException
import ru.devgroup.adventuremap.domain.exceptions.ServerError
import ru.devgroup.adventuremap.domain.repository.MediaRepository

@RestController
@RequestMapping(MEDIA_API)
class MediaController(
    private val mediaRepository: MediaRepository,
    private val storageService: MediaRepository,
) {
    @ExceptionHandler(Exception::class, BaseException::class)
    fun exceptionHandler(e: Exception): ResponseEntity<Any> {
        return when (e) {
            is BaseException -> {
                ResponseEntity.status(e.statusCode).body(e)
            }
            else -> {
                ResponseEntity.status(500).body(ServerError(description = e.message))
            }
        }
    }

    // TODO("/load", "/delete", "/upload")
}
