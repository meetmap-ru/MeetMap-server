package ru.devgroup.adventuremap.data.util

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.domain.exceptions.ServerError
import ru.devgroup.adventuremap.domain.util.MediaUtils
import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.Inflater

@Component
class MediaUtilsImpl : MediaUtils {
    override fun compressMedia(media: ByteArray): ByteArray {
        val deflater = Deflater()
        deflater.setLevel(Deflater.BEST_COMPRESSION)
        deflater.setInput(media)
        deflater.finish()

        val output = ByteArrayOutputStream(media.size)
        val tmp = ByteArray(4 * 1024)
        while (!deflater.finished()) {
            val size = deflater.deflate(tmp)
            output.write(tmp, 0, size)
        }
        try {
            output.close()
            return output.toByteArray()
        } catch (e: Exception) {
            throw ServerError(description = "Exception during compression: " + e.message)
        }
    }

    override fun decompressMedia(compressed: ByteArray): ByteArray {
        val inflater = Inflater()
        inflater.setInput(compressed)
        val output = ByteArrayOutputStream(compressed.size)
        val tmp = ByteArray(4 * 1024)
        try {
            while (!inflater.finished()) {
                val count = inflater.inflate(tmp)
                output.write(tmp, 0, count)
            }
            output.close()
            return output.toByteArray()
        } catch (e: Exception) {
            throw ServerError(description = "Exception during decompression: " + e.message)
        }
    }
}
