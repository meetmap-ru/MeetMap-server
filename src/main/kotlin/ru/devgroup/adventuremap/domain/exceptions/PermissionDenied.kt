package ru.devgroup.adventuremap.domain.exceptions

import com.fasterxml.jackson.annotation.JsonPropertyOrder

class PermissionDenied(
    description: String? = null
): BaseException(
    message = "permission denied",
    statusCode = 403,
    description = description
)