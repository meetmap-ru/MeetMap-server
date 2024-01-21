package ru.devgroup.adventuremap.domain.repository

import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.domain.model.user.User

interface UserRepository {
    fun signUp(user: User, password: String): State<User>

    fun signIn(login: String, password: String): State<User>

    fun deleteAccount(id: Long, password: String): State<Unit>

    fun verify(id: Long): State<Unit>

    fun getById(id: Long, private: Boolean = true): State<User>

    fun getByPhoneNumber(phone: String): State<User>

    fun getByEmail(email: String): State<User>

    fun getByUsername(username: String): State<User>

    fun getByIds(vararg ids: Long): State<List<User>>

    fun getByCity(city: Long): State<List<User>>

    fun getByName(name: String): State<List<User>>

    fun setPassword(id: Long, oldPassword: String, password: String): State<Unit>

    fun setDescription(id: Long, description: String): State<Unit>

    fun setLastSeen(id: Long): State<Unit>

    fun setAvatar(id: Long, avatar: Long): State<Unit>

    fun deleteAvatar(id: Long, avatar: Long): State<Unit>

    fun setSubscriber(id: Long, subscriber: Long): State<Unit>

    fun deleteSubscriber(id: Long, subscriber: Long): State<Unit>

    fun setCategory(id: Long, category: Long): State<Unit>

    fun deleteCategory(id: Long, category: Long): State<Unit>

    fun setEvent(id: Long, event: Long): State<Unit>

    fun deleteEvent(id: Long, event: Long): State<Unit>

    fun setHistoryEvent(id: Long, event: Long): State<Unit>

    fun deleteHistoryEvent(id: Long, event: Long): State<Unit>
}