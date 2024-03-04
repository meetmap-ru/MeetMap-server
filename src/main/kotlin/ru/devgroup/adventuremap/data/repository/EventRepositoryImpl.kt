package ru.devgroup.adventuremap.data.repository

import org.springframework.stereotype.Repository
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.EventDao
import ru.devgroup.adventuremap.data.entity.EventEntity
import ru.devgroup.adventuremap.data.util.EventDomainConverter
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.exceptions.PermissionDenied
import ru.devgroup.adventuremap.domain.model.Permission
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
        category: List<Long>,
        cityId: Long?,
    ): State<List<Event>> {
        val entity: List<EventEntity> = eventDao.findByKeyWord(word)
        return State.Success(
            entity
                .filter {
                    (it.category intersect category).isNotEmpty() && it.cityId == (cityId ?: it.cityId)
                }
                .map { it.asDomain() },
        )
    }

    override fun createEvent(event: Event): State<Event> {
        val entity = eventDao.save(event.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun deleteEvent(
        eventId: Long,
        authorId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(authorId, Permission.OnlyOwner) }
                ?: throw PermissionDenied()
        eventDao.delete(entity)
        return State.Success(Unit)
    }

    override fun addEventMember(
        eventId: Long,
        memberId: Long,
        organizerId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(organizerId, event.memberChangePermission) }
                ?: throw PermissionDenied()

        eventDao.save(entity.copy(members = entity.members + memberId))
        return State.Success(Unit)
    }

    override fun deleteEventMember(
        eventId: Long,
        memberId: Long,
        organizerId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(organizerId, event.memberChangePermission) }
                ?: throw PermissionDenied()
        eventDao.save(entity.copy(members = entity.members - memberId))
        return State.Success(Unit)
    }

    override fun addMedia(
        eventId: Long,
        mediaId: Long,
        organizerId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(organizerId, event.mediaChangePermission) }
                ?: throw PermissionDenied()
        eventDao.save(entity.copy(media = entity.media + mediaId))
        return State.Success(Unit)
    }

    override fun deleteMedia(
        eventId: Long,
        mediaId: Long,
        organizerId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(organizerId, event.mediaChangePermission) }
                ?: throw PermissionDenied()
        eventDao.save(entity.copy(media = entity.media - mediaId))
        return State.Success(Unit)
    }

    override fun addOrganizer(
        eventId: Long,
        organizerId: Long,
        ownerId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(organizerId, Permission.OnlyOwner) }
                ?: throw PermissionDenied()
        eventDao.save(entity.copy(organizers = entity.organizers + organizerId))
        return State.Success(Unit)
    }

    override fun deleteOrganizer(
        eventId: Long,
        organizerId: Long,
        ownerId: Long,
    ): State<Unit> {
        val entity =
            eventDao.findById(eventId)
                .getOrElse { throw NotFoundException() }
                .takeIf { event -> event.isActionPermitted(organizerId, Permission.OnlyOwner) }
                ?: throw PermissionDenied()

        eventDao.save(entity.copy(organizers = entity.organizers - organizerId))
        return State.Success(Unit)
    }

    private fun EventEntity.isActionPermitted(
        userId: Long,
        action: Permission,
    ): Boolean {
        if (userId == owner) return true
        if (userId in organizers && action != Permission.OnlyOwner) return true
        if (userId in members && action == Permission.OnlyMembers) return true
        if (action == Permission.AllUsers) return true
        return false
    }

    override fun isActionPermitted(
        event: Event,
        userId: Long,
        action: Permission,
    ): Boolean =
        event
            .asDatabaseEntity()
            .isActionPermitted(userId, action)
}
