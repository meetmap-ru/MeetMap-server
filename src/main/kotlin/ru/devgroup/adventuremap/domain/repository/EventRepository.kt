package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.event.Category
import ru.devgroup.adventuremap.domain.model.event.Event

interface EventRepository {
    fun getEventById(id: Long): State<Event>

    fun getEventByCity(cityId: Long): State<List<Event>>

    fun getEventByKeyWord(word: String): State<List<Event>>

    fun getEventByWordFiltered(
        word: String,
        category: List<Category>,
    ): State<List<Event>>

    fun createEvent(event: Event): State<Event>

    fun deleteEvent(
        eventId: Long,
        authorId: Long,
    ): State<Unit>

    fun addEventMember(
        eventId: Long,
        memberId: Long,
    ): State<Unit>

    fun deleteEventMember(
        eventId: Long,
        memberId: Long,
    ): State<Unit>
}
