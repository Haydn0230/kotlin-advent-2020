package models.message

import com.feedme.models.Event
import com.feedme.models.Header
import com.feedme.models.Market
import com.feedme.models.Message
import com.feedme.models.Outcome
import com.feedme.models.Type
import com.feedme.models.createMessage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MessageTest {
    private val testCases = mapOf(
        Type.OUTCOME to listOf(
            "45567",
            "update",
            "outcome",
            "1652879071966",
            "0c6d9e95-ca92-482c-b568-1ab83b5f36e7",
            "d7bf2e18-a0ea-47f4-82e2-1809227e49ae",
            "Derby",
            "8/11",
            "1",
            "0"
        ),
        Type.EVENT to listOf(
            "45609",
            "create",
            "event",
            "1652886092191",
            "30b7c1cf-1964-4ffe-be66-573efb9b6263",
            "Football",
            "Sky Bet League Two",
            "Accrington vs Chesterfield",
            "1652879109109",
            "0",
            "1"
        ),
        Type.MARKET to listOf(
            "45610",
            "create",
            "market",
            "1652886092192",
            "30b7c1cf-1964-4ffe-be66-573efb9b6263",
            "c50eead7-8aed-4145-b00e-25305b39c5c4",
            "Full Time Result",
            "0",
            "1"
        )
    )
    val expectedResults = mapOf<Type, Message>(
        Type.OUTCOME to Message(
            header = Header(msgId = 45567, operation = "update", type = "outcome", 1652879071966),
            body = Outcome(
                marketId = "0c6d9e95-ca92-482c-b568-1ab83b5f36e7",
                outcomeId = "d7bf2e18-a0ea-47f4-82e2-1809227e49ae",
                name = "Derby",
                price = "8/11",
                displayed = true,
                suspended = false
            )
        ),
        Type.EVENT to Message(
            header = Header(msgId = 45609, operation = "create", type = "event", timeStamp = 1652886092191),
            body = Event(
                eventId = "30b7c1cf-1964-4ffe-be66-573efb9b6263",
                category = "Football",
                subCategory = "Sky Bet League Two",
                startTime = 1652879109109,
                name = "Accrington vs Chesterfield",
                displayed = false,
                suspended = true
            )
        ),
        Type.MARKET to Message(
            header = Header(
                msgId = 45610,
                operation = "create",
                type = "market",
                timeStamp = 1652886092192,
            ),
            body = Market(
                eventId = "30b7c1cf-1964-4ffe-be66-573efb9b6263",
                marketId = "c50eead7-8aed-4145-b00e-25305b39c5c4",
                name = "Full Time Result",
                displayed = false,
                suspended = true
            )
        )
    )
    // test factory / paramaterise test
    @Test
    // improve message for tests
    fun `returns an outcome message type`() {
        val result = testCases[Type.OUTCOME]?.let { createMessage(it) }
        assertEquals(expectedResults[Type.OUTCOME], result)
    }

    @Test
    fun `returns an event message type`() {
        val result = testCases[Type.EVENT]?.let { createMessage(it) }
        assertEquals(expectedResults[Type.EVENT], result)
    }

    @Test
    fun `returns an market message type`() {
        val result = testCases[Type.MARKET]?.let { createMessage(it) }
        assertEquals(expectedResults[Type.MARKET], result)
    }
}
