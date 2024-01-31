package ru.devgroup.adventuremap.data.dao

import org.springframework.data.jpa.repository.JpaRepository
import ru.devgroup.adventuremap.data.entity.EventEntity

interface EventDao : JpaRepository<EventEntity, Long>
