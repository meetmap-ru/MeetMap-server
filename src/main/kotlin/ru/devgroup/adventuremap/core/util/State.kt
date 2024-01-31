package ru.devgroup.adventuremap.core.util

import org.springframework.http.ResponseEntity
import ru.devgroup.adventuremap.domain.response.Response

/**
 * Олицетворяет состояние запроса, выполнения функции, используется начиная с репозиториев и до контроллеров
 */
sealed class State<T>(
    open val data: T?,
    val message: String,
    open val status: Int,
) {
    class Completed(
        message: String?,
        status: Int,
    ) : State<Unit>(null, "completed", status)

    class Success<T>(
        override val data: T,
        message: String? = null,
        override val status: Int = 200,
    ) : State<T>(data, message ?: "success", status)

    class Error<T>(
        override val data: T? = null,
        message: String? = null,
        override val status: Int,
    ) : State<T>(data, message ?: "error", status)

    fun asResponse(): ResponseEntity<Any> =
        ResponseEntity.status(status).body(
            Response.DataResponse(
                data,
                message,
            ),
        )
}
