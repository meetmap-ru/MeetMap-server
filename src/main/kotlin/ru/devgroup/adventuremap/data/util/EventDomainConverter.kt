package ru.devgroup.adventuremap.data.util

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.data.entity.EventEntity
import ru.devgroup.adventuremap.domain.model.event.Event
import ru.devgroup.adventuremap.domain.usecase.GetCategoryByIdUseCase
import ru.devgroup.adventuremap.domain.usecase.GetMediaByIdUseCase
import ru.devgroup.adventuremap.domain.usecase.GetUserByIdUseCase
import ru.devgroup.adventuremap.domain.util.DomainConverter

/**
 * DomainConverter для EventEntity <-> Event
 */

@Component
class EventDomainConverter : DomainConverter<EventEntity, Event> {
    private lateinit var getUserByIdUseCase: GetUserByIdUseCase
    private lateinit var getMediaByIdUseCase: GetMediaByIdUseCase
    private lateinit var getCategoryByIdUseCase: GetCategoryByIdUseCase

    override fun Event.asDatabaseEntity(): EventEntity =
        EventEntity(
            title,
            media.map { it.id },
            description,
            members,
            category.map { it.id },
            ageLimit,
            date,
            cityId,
            address,
            coordinates,
            owner,
            organizers,
            timestamp,
            chat,
            maxPersons,
            price,
            mediaVisibility,
            mediaChangePermission,
            chatVisibility,
            membersVisibility,
            memberChangePermission,
        )

    override fun EventEntity.asDomain(): Event =
        Event(
            title,
            media.mapNotNull { getMediaByIdUseCase(it).data },
            description,
            members,
            category.mapNotNull { getCategoryByIdUseCase(it).data },
            ageLimit,
            date,
            cityId,
            address,
            coordinates,
            owner,
            organizers,
            timestamp,
            chat,
            maxPersons,
            price,
            mediaVisibility,
            mediaChangePermission,
            chatVisibility,
            membersVisibility,
            memberChangePermission,
        )
}
