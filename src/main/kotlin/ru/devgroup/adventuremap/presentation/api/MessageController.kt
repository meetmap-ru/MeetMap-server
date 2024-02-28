package ru.devgroup.adventuremap.presentation.api

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.devgroup.adventuremap.core.config.MESSAGE_API
import ru.devgroup.adventuremap.domain.exceptions.BaseException
import ru.devgroup.adventuremap.domain.exceptions.ServerError
import ru.devgroup.adventuremap.domain.repository.MessageRepository
import ru.devgroup.adventuremap.domain.request.message.EditMessageRequest
import ru.devgroup.adventuremap.domain.request.message.ListMessagesRequest
import ru.devgroup.adventuremap.domain.util.PaginationController
import ru.devgroup.adventuremap.domain.util.TokenHelper

@RestController
@RequestMapping(MESSAGE_API)
class MessageController(
    private val messageRepository: MessageRepository,
    private val tokenHelper: TokenHelper,
) : PaginationController {
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

    @GetMapping("/get/by-id")
    fun getMessageById(
        @RequestBody body: ListMessagesRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = tokenHelper.getClaims(token)?.get("id") as Long
        return messageRepository.getMessageById(userId, *body.id.toLongArray()).asResponse()
    }

    @GetMapping("/get/by-event")
    fun getMessageByEvent(
        @RequestParam id: Long,
        @RequestParam page: Int,
        @RequestParam limit: Int,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = tokenHelper.getClaims(token)?.get("id") as Long
        return messageRepository.getMessageByEvent(userId, limit, page, id).asResponse()
    }

    @PostMapping("/post/delete")
    fun deleteMessageById(
        @RequestBody body: ListMessagesRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = tokenHelper.getClaims(token)?.get("id") as Long
        return messageRepository.deleteMessage(userId, *body.id.toLongArray()).asResponse()
    }

    @PostMapping("/post/edit")
    fun editMessageById(
        @RequestBody body: EditMessageRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = tokenHelper.getClaims(token)?.get("id") as Long
        return messageRepository.editMessage(userId, body.id, body.media, body.replyTo, body.text).asResponse()
    }
}
