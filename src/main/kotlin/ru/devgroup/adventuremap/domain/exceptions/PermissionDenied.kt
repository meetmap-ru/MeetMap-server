package ru.devgroup.adventuremap.domain.exceptions

class PermissionDenied(
    description: String? = null,
) : BaseException(
        message = "permission denied",
        statusCode = 403,
        description = description,
    )
