package ru.devgroup.adventuremap.data.util

import ru.devgroup.adventuremap.data.entity.MediaEntity
import ru.devgroup.adventuremap.domain.model.media.Media
import ru.devgroup.adventuremap.domain.util.DomainConverter

open class MediaDomainConverter : DomainConverter<MediaEntity, Media> {
    override fun Media.asDatabaseEntity(): MediaEntity =
        MediaEntity(
            id,
            name,
            type,
            data,
        )

    override fun MediaEntity.asDomain(): Media =
        Media(
            id,
            name,
            type,
            data,
        )
}
