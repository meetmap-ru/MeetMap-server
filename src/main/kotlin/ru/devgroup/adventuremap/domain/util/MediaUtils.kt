package ru.devgroup.adventuremap.domain.util

interface MediaUtils {
    fun compressMedia(media: ByteArray): ByteArray

    fun decompressMedia(media: ByteArray): ByteArray
}
