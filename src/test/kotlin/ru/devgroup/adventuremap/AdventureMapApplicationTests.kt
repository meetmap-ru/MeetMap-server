package ru.devgroup.adventuremap

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.devgroup.adventuremap.data.util.EventDomainConverter
import ru.devgroup.adventuremap.domain.model.event.Event
import ru.devgroup.adventuremap.domain.model.media.Media

@SpringBootTest
class AdventureMapApplicationTests {
    @Test
    fun contextLoads() {
        val d = EventDomainConverter()
        val e =
            Event(
                "title",
                listOf(Media("cum", "cock", "pick")),
                "sdvsdvsdvsdv",
                listOf(),
                listOf(),
                12,
                111L to 1111L,
                12,
                "sdvsdv",
                listOf(),
                11111L,
                0,
                0,
                0.0f,
            )
        println(d.doConvert(e))
    }
}
