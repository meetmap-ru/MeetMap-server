package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.Permission
import ru.devgroup.adventuremap.domain.model.event.Event

interface EventRepository {
    fun getEventById(id: Long): State<Event>

    fun getEventByCity(cityId: Long): State<List<Event>>

    fun getEventByKeyWord(word: String): State<List<Event>>

    fun getEventByWordFiltered(
        word: String,
        category: List<Long>,
        cityId: Long?,
    ): State<List<Event>>

    fun createEvent(event: Event): State<Event>

    fun deleteEvent(
        eventId: Long,
        authorId: Long,
    ): State<Unit>

    fun addEventMember(
        eventId: Long,
        memberId: Long,
        organizerId: Long,
    ): State<Unit>

    fun deleteEventMember(
        eventId: Long,
        memberId: Long,
        organizerId: Long,
    ): State<Unit>

    fun addMedia(
        eventId: Long,
        mediaId: Long,
        organizerId: Long,
    ): State<Unit>

    fun deleteMedia(
        eventId: Long,
        mediaId: Long,
        organizerId: Long,
    ): State<Unit>

    fun addOrganizer(
        eventId: Long,
        organizerId: Long,
        ownerId: Long,
    ): State<Unit>

    fun deleteOrganizer(
        eventId: Long,
        organizerId: Long,
        ownerId: Long,
    ): State<Unit>

    fun isActionPermitted(
        event: Event,
        userId: Long,
        action: Permission,
    ): Boolean
}
