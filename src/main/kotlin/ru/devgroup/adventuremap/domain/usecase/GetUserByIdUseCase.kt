package ru.devgroup.adventuremap.domain.usecase

import org.springframework.stereotype.Component
import ru.devgroup.adventuremap.domain.repository.UserRepository

@Component
class GetUserByIdUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        id: Long,
        private: Boolean = false,
    ) = userRepository.getById(id, private)
}
