package ru.devgroup.adventuremap.presentation.api.event

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.devgroup.adventuremap.core.config.EVENT_API
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.exceptions.BaseException
import ru.devgroup.adventuremap.domain.exceptions.ServerError
import ru.devgroup.adventuremap.domain.model.event.Event
import ru.devgroup.adventuremap.domain.repository.EventRepository
import ru.devgroup.adventuremap.domain.repository.UserRepository
import ru.devgroup.adventuremap.domain.request.event.ChangeEventItemIdRequest
import ru.devgroup.adventuremap.domain.request.event.CreateEventRequest
import ru.devgroup.adventuremap.domain.request.event.DeleteEventRequest
import ru.devgroup.adventuremap.domain.request.event.GetEventRequest
import ru.devgroup.adventuremap.domain.util.TokenHelper

@RestController
@RequestMapping(EVENT_API)
class EventController(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository,
    private val tokenHelper: TokenHelper,
) {
    @ExceptionHandler(Exception::class, BaseException::class)
    fun exceptionHandler(e: Exception): ResponseEntity<Any> {
        return when (e) {
            is BaseException -> {
                ResponseEntity.status(e.statusCode).body(e)
            }

            else -> {
                ResponseEntity.status(500).body(ServerError(description = e.message))
            }
        }
    }

    @GetMapping("/get")
    fun getEvent(
        @RequestBody searchParams: GetEventRequest,
    ): ResponseEntity<Any> {
        if (searchParams.id != null) {
            return eventRepository.getEventById(searchParams.id).asResponse()
        }
        return eventRepository.getEventByWordFiltered(
            word = searchParams.keyWord ?: "",
            category = searchParams.categories,
            cityId = searchParams.cityId,
        ).asResponse()
    }

    @PostMapping("/post/create")
    fun createEvent(
        @RequestBody eventParams: CreateEventRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        // TODO("support chat dao")
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        val event: Event =
            Event.new(
                title = eventParams.title,
                description = eventParams.description,
                category = eventParams.category,
                ageLimit = eventParams.ageLimit,
                date = eventParams.date,
                cityId = eventParams.cityId,
                address = eventParams.address,
                coordinates = eventParams.coordinates,
                owner = userId,
                organizers = listOf(userId),
                timestamp = System.currentTimeMillis(),
                chat = 0,
                maxPersons = eventParams.maxPersons,
                price = eventParams.price,
                mediaVisibility = eventParams.mediaVisibility,
                mediaChangePermission = eventParams.mediaChangePermission,
                chatVisibility = eventParams.chatVisibility,
                membersVisibility = eventParams.mediaVisibility,
                memberChangePermission = eventParams.mediaChangePermission,
            )
        val state = eventRepository.createEvent(event)
        if (state is State.Success) {
            userRepository.setEvent(
                id = userId,
                event = state.data.id,
            )
        }
        return state.asResponse()
    }

    @PostMapping("post/delete")
    fun deleteEvent(
        @RequestBody eventParams: DeleteEventRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.deleteEvent(
            eventId = eventParams.id,
            authorId = userId,
        ).asResponse()
    }

    @PostMapping("post/add-member")
    fun addMember(
        @RequestBody eventParams: ChangeEventItemIdRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.addEventMember(
            eventId = eventParams.eventId,
            memberId = eventParams.itemId,
            organizerId = userId,
        ).asResponse()
    }

    @PostMapping("post/delete-member")
    fun deleteMember(
        @RequestBody eventParams: ChangeEventItemIdRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.deleteEventMember(
            eventId = eventParams.eventId,
            memberId = eventParams.itemId,
            organizerId = userId,
        ).asResponse()
    }

    @PostMapping("post/add-media")
    fun addMedia(
        @RequestBody eventParams: ChangeEventItemIdRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.addMedia(
            eventId = eventParams.eventId,
            mediaId = eventParams.itemId,
            organizerId = userId,
        ).asResponse()
    }

    @PostMapping("post/delete-media")
    fun deleteMedia(
        @RequestBody eventParams: ChangeEventItemIdRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.deleteMedia(
            eventId = eventParams.eventId,
            mediaId = eventParams.itemId,
            organizerId = userId,
        ).asResponse()
    }

    @PostMapping("post/add-organizer")
    fun addOrganizer(
        @RequestBody eventParams: ChangeEventItemIdRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.addOrganizer(
            eventId = eventParams.eventId,
            organizerId = eventParams.itemId,
            ownerId = userId,
        ).asResponse()
    }

    @PostMapping("post/delete-organizer")
    fun deleteOrganizer(
        @RequestBody eventParams: ChangeEventItemIdRequest,
        @RequestHeader header: HttpHeaders,
    ): ResponseEntity<Any> {
        val token = header["Authorization"]?.first()
        val userId = (tokenHelper.getClaims(token)?.get("id") as Int).toLong()
        return eventRepository.deleteOrganizer(
            eventId = eventParams.eventId,
            organizerId = eventParams.itemId,
            ownerId = userId,
        ).asResponse()
    }
}
