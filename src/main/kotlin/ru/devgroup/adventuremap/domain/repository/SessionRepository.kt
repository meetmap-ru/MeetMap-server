package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.user.Session

interface SessionRepository {
    fun getSessionByUserId(id: Long): State<List<Session>>

    fun getSessionById(id: Long): Session?

    fun saveSession(session: Session): State<Session>
}