package ru.devgroup.adventuremap.domain.util

import ru.devgroup.adventuremap.data.model.DatabaseEntity
import ru.devgroup.adventuremap.domain.model.Domain

/**
 * DomainConverter нужен для конвертации Domain классов в DatabaseEntity классы
 * Для нужных классов нужно реализовать класс-конвертер
 * Наследуется репозиториями
 */

interface DomainConverter<T : DatabaseEntity, R : Domain> {
    fun R.asDatabaseEntity(): T

    fun T.asDomain(): R
}
