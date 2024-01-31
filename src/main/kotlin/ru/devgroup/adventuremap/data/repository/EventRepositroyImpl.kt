package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Repository
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.EventDao
import ru.devgroup.adventuremap.data.util.EventDomainConverter
import ru.devgroup.adventuremap.domain.model.event.Category
import ru.devgroup.adventuremap.domain.model.event.Event
import ru.devgroup.adventuremap.domain.repository.EventRepository

@Repository
class EventRepositoryImpl(
    private val eventDao: EventDao,
) : EventRepository, EventDomainConverter() {
    override fun getEventById(id: Long): State<Event> {
        TODO("Not yet implemented")
    }

    override fun getEventByCity(cityId: Long): State<Event> {
        TODO("Not yet implemented")
    }

    override fun getEventByKeyWord(word: String): State<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun filterByCategories(category: List<Category>): State<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun createEvent(event: Event): State<Event> {
        TODO("Not yet implemented")
    }

    override fun deleteEvent(
        eventId: Event,
        authorId: Long,
    ): State<Unit> {
        TODO("Not yet implemented")
    }

    override fun addEventMember(
        eventId: Event,
        memberId: Long,
    ): State<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteEventMember(
        eventId: Event,
        memberId: Long,
    ): State<Unit> {
        TODO("Not yet implemented")
    }
}
