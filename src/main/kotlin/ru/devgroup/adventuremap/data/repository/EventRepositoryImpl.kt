package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Repository
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.EventDao
import ru.devgroup.adventuremap.data.entity.EventEntity
import ru.devgroup.adventuremap.data.util.EventDomainConverter
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.model.event.Category
import ru.devgroup.adventuremap.domain.model.event.Event
import ru.devgroup.adventuremap.domain.repository.EventRepository
import kotlin.jvm.optionals.getOrElse

@Repository
class EventRepositoryImpl(
    private val eventDao: EventDao,
) : EventRepository, EventDomainConverter() {
    override fun getEventById(id: Long): State<Event> {
        val entity: EventEntity = eventDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getEventByCity(cityId: Long): State<List<Event>> {
        val entity: List<EventEntity> = eventDao.findByCityId(cityId)
        return State.Success(entity.map { it.asDomain() })
    }

    override fun getEventByKeyWord(word: String): State<List<Event>> {
        val entity: List<EventEntity> = eventDao.findByKeyWord(word)
        return State.Success(entity.map { it.asDomain() })
    }

    override fun getEventByWordFiltered(
        word: String,
        category: List<Category>,
    ): State<List<Event>> {
        val entity: List<EventEntity> = eventDao.findByKeyWord(word)
        return State.Success(entity.filter { (it.category intersect category).isNotEmpty() }.map { it.asDomain() })
    }

    override fun createEvent(event: Event): State<Event> {
        val entity = eventDao.save(event.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun deleteEvent(
        eventId: Long,
        authorId: Long,
    ): State<Unit> {
        val entity = eventDao.findById(eventId).getOrElse { throw NotFoundException() }
        eventDao.delete(entity)
        return State.Success(Unit)
    }

    override fun addEventMember(
        eventId: Long,
        memberId: Long,
    ): State<Unit> {
        val entity = eventDao.findById(eventId).getOrElse { throw NotFoundException() }
        eventDao.save(entity.copy(members = entity.members + memberId))
        return State.Success(Unit)
    }

    override fun deleteEventMember(
        eventId: Long,
        memberId: Long,
    ): State<Unit> {
        val entity = eventDao.findById(eventId).getOrElse { throw NotFoundException() }
        eventDao.save(entity.copy(members = entity.members - memberId))
        return State.Success(Unit)
    }
}
