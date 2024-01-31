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
            members.map { it.id },
            category.map { it.id },
            ageLimit,
            date,
            cityId,
            address,
            organizers.map { it.id },
            timestamp,
            chat,
            maxPersons,
            price,
            mediaVisibility,
            chatVisibility,
            membersVisibility,
        )

    override fun EventEntity.asDomain(): Event =
        Event(
            title,
            media.mapNotNull { getMediaByIdUseCase(it).data },
            description,
            members.mapNotNull { getUserByIdUseCase(it, false).data },
            category.mapNotNull { getCategoryByIdUseCase(it).data },
            ageLimit,
            date,
            cityId,
            address,
            organizers.mapNotNull { getUserByIdUseCase(it, false).data },
            timestamp,
            chat,
            maxPersons,
            price,
            mediaVisibility,
            chatVisibility,
            membersVisibility,
        )

    fun doConvert(e: Event): EventEntity {
        println("sdcsdc")
        return e.asDatabaseEntity()
    }
}
