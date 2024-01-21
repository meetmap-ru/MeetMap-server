package ru.devgroup.adventuremap.domain.exceptions


class InvalidRegistrationData(
    description: String?=null
): BaseException(
    description = description,
    message = "invalid registration data",
    statusCode = 406
)