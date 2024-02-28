package ru.devgroup.adventuremap.presentation.api

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import ru.devgroup.adventuremap.core.config.USER_API
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.exceptions.BaseException
import ru.devgroup.adventuremap.domain.exceptions.ServerError
import ru.devgroup.adventuremap.domain.model.user.User
import ru.devgroup.adventuremap.domain.repository.UserRepository
import ru.devgroup.adventuremap.domain.request.user.ResetPasswordRequest
import ru.devgroup.adventuremap.domain.request.user.SignInRequest
import ru.devgroup.adventuremap.domain.request.user.SignUpRequest
import ru.devgroup.adventuremap.domain.response.Response.DataResponse
import ru.devgroup.adventuremap.domain.util.PaginationController
import ru.devgroup.adventuremap.domain.util.TokenHelper

@RestController
@RequestMapping(USER_API)
class UserController(
    private val userRepository: UserRepository,
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

    @PostMapping("/post/sign-up")
    fun signUp(
        @RequestBody registerRequest: SignUpRequest,
        result: BindingResult,
    ): ResponseEntity<Any> = userRepository.signUp(registerRequest.asDomain(), registerRequest.password).asResponse()

    @GetMapping("/get/sign-in")
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
        result: BindingResult,
    ): ResponseEntity<Any> =
        when (val state = userRepository.signIn(signInRequest.username, signInRequest.password)) {
            is State.Success -> {
                val token = tokenHelper.createToken(state.data as User)
                ResponseEntity.accepted().body(DataResponse(token, message = "authorized"))
            }
            else -> state.asResponse()
        }

    @GetMapping("/get/info")
    fun getUserInfo(
        @RequestHeader header: HttpHeaders,
        @RequestParam id: Long,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return userRepository.getById(id, id == userId).asResponse()
    }

    @PostMapping("post/reset-password")
    fun resetPassword(
        @RequestHeader header: HttpHeaders,
        @RequestBody resetRequest: ResetPasswordRequest,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return userRepository.setPassword(userId, resetRequest.oldPassword, resetRequest.password).asResponse()
    }

    @GetMapping("get/by-city")
    fun getByCity(
        @RequestParam id: Long,
        @RequestParam limit: Int = 65,
        @RequestParam offset: Int = 0,
    ): ResponseEntity<Any> {
        return userRepository.getByCity(id).page(limit, offset).asResponse()
    }

    @GetMapping("get/by-name")
    fun getByName(
        @RequestParam name: String,
        @RequestParam limit: Int = 65,
        @RequestParam offset: Int = 0,
    ): ResponseEntity<Any> {
        return userRepository.getByName(name).page(limit, offset).asResponse()
    }
}
