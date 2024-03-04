package ru.devgroup.adventuremap.domain.request.media

import org.springframework.web.multipart.MultipartFile

data class CreateMediaRequest(
    val name: String,
    val link: String,
    val type: String,
    val media: MultipartFile,
)
