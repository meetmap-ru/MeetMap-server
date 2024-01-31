package ru.devgroup.adventuremap.presentation.api.event

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.devgroup.adventuremap.core.config.EVENT_API
import ru.devgroup.adventuremap.core.config.USER_API
import ru.devgroup.adventuremap.domain.repository.EventRepository
import ru.devgroup.adventuremap.domain.repository.UserRepository
import ru.devgroup.adventuremap.domain.util.TokenHelper

@RestController
@RequestMapping(EVENT_API)
class EventController(
    private val eventRepository: EventRepository,
    private val tokenHelper: TokenHelper
) {

}