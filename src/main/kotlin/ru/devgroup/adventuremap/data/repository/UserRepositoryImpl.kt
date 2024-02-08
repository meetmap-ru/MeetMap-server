package ru.devgroup.adventuremap.data.repository

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import ru.devgroup.adventuremap.core.util.State
import ru.devgroup.adventuremap.data.dao.UserDao
import ru.devgroup.adventuremap.data.util.UserDomainConverter
import ru.devgroup.adventuremap.domain.exceptions.InvalidCredentials
import ru.devgroup.adventuremap.domain.exceptions.NotFoundException
import ru.devgroup.adventuremap.domain.model.user.User
import ru.devgroup.adventuremap.domain.repository.UserRepository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserRepositoryImpl(
    private val userDao: UserDao,
    private val passwordEncoder: PasswordEncoder,
) : UserRepository, UserDomainConverter() {
    override fun signUp(
        user: User,
        password: String,
    ): State<User> {
        return State.Success(
            userDao.save(
                user.asDatabaseEntity().copy(
                    password = passwordEncoder.encode(password),
                    registrationTimestamp = System.currentTimeMillis(),
                ),
            ).asDomain(),
        )
    }

    override fun signIn(
        login: String,
        password: String,
    ): State<User> {
        val user = userDao.findByLogin(login) ?: throw NotFoundException()
        if (user.password != passwordEncoder.encode(password)) throw InvalidCredentials()
        return State.Success(user.asDomain())
    }

    override fun deleteAccount(
        id: Long,
        password: String,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException("user does not exist")
        userDao.delete(user.copy(password = passwordEncoder.encode(password)))
        return State.Completed("user deleted", 200)
    }

    override fun verify(id: Long): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException("user does not exist")
        userDao.save(user.copy(verified = true)).let {
            return State.Completed("user verified", 200)
        }
    }

    override fun getById(
        id: Long,
        private: Boolean,
    ): State<User> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        return State.Success(
            user.asDomain().apply {
                if (!private) commonInfo()
            },
        )
    }

    override fun getByPhoneNumber(phone: String): State<User> {
        val user = userDao.findByPhoneNumber(phone) ?: throw NotFoundException()
        return State.Success(user.asDomain().commonInfo())
    }

    override fun getByEmail(email: String): State<User> {
        val user = userDao.findByEmail(email) ?: throw NotFoundException()
        return State.Success(user.asDomain().commonInfo())
    }

    override fun getByUsername(username: String): State<User> {
        val user = userDao.findByUsername(username) ?: throw NotFoundException()
        return State.Success(user.asDomain().commonInfo())
    }

    override fun getByIds(vararg ids: Long): State<List<User>> {
        val userList: List<User> =
            ids.asList().mapNotNull { id ->
                userDao.findById(id).getOrNull()?.asDomain()?.commonInfo()
            }
        return State.Success(userList)
    }

    override fun getByCity(city: Long): State<List<User>> {
        val userList: List<User> = userDao.findByCity(city).map { it.asDomain().commonInfo() }
        return State.Success(userList)
    }

    override fun getByName(name: String): State<List<User>> {
        val userList: List<User> = userDao.findByName(name).map { it.asDomain().commonInfo() }
        return State.Success(userList)
    }

    override fun setPassword(
        id: Long,
        oldPassword: String,
        password: String,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        if (user.password != passwordEncoder.encode(oldPassword)) throw InvalidCredentials()
        userDao.save(user.copy(password = passwordEncoder.encode(password)))
        return State.Completed("password changed", 200)
    }

    override fun setDescription(
        id: Long,
        description: String,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(description = description))
        return State.Completed("description added", 200)
    }

    override fun setLastSeen(id: Long): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(lastSeen = System.currentTimeMillis()))
        return State.Completed("last seen updated", 200)
    }

    override fun setAvatar(
        id: Long,
        avatar: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(avatars = (user.avatars ?: emptyList()) + avatar))
        return State.Completed("avatar added", 200)
    }

    override fun deleteAvatar(
        id: Long,
        avatar: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(avatars = (user.avatars ?: emptyList()) - avatar))
        return State.Completed("avatar deleted", 200)
    }

    override fun setSubscriber(
        id: Long,
        subscriber: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(subscribers = (user.subscribers ?: emptyList()) + subscriber))
        return State.Completed("subscriber added", 200)
    }

    override fun deleteSubscriber(
        id: Long,
        subscriber: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(subscribers = (user.subscribers ?: emptyList()) - subscriber))
        return State.Completed("subscriber deleted", 200)
    }

    override fun setCategory(
        id: Long,
        category: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(categories = (user.categories ?: emptyList()) + category))
        return State.Completed("category added", 200)
    }

    override fun deleteCategory(
        id: Long,
        category: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(categories = (user.categories ?: emptyList()) - category))
        return State.Completed("category deleted", 200)
    }

    override fun setEvent(
        id: Long,
        event: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(events = (user.events ?: emptyList()) + event))
        return State.Completed("event added", 200)
    }

    override fun deleteEvent(
        id: Long,
        event: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(events = (user.events ?: emptyList()) - event))
        return State.Completed("event deleted", 200)
    }

    override fun setHistoryEvent(
        id: Long,
        event: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(history = (user.history ?: emptyList()) + event))
        return State.Completed("history added", 200)
    }

    override fun deleteHistoryEvent(
        id: Long,
        event: Long,
    ): State<Unit> {
        val user = userDao.findById(id).getOrNull() ?: throw NotFoundException()
        userDao.save(user.copy(history = (user.history ?: emptyList()) - event))
        return State.Completed("history deleted", 200)
    }
}
