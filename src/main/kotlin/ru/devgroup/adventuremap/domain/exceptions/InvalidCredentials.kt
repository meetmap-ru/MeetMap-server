package ru.devgroup.adventuremap.domain.exceptions

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.context.annotation.Description

class InvalidCredentials(
    description: String? = null
): BaseException(
    message = "invalid credentials",
    statusCode = 406,
    description = description
)