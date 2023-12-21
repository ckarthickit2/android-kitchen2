package me.kartdroid.androidkitchen2.kotlinxserialization

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.Test

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 16/11/23
 */
class ServiceResponseTest {

    @Test
    fun kotlinxGenericsTest() {
        val serviceResponse = ServiceResponse(
                listOf(
                        Service("id1", ServiceId("a1", "app")),
                        Service("id2", ServiceId("a2", "auto")),
                        Service("id4", ServiceId("a4", "delivery")),
                )
        )
        val serializedResponse = Json.encodeToJsonElement(serviceResponse).toString()
        println(serializedResponse)
        val deserialization = Json.decodeFromString<ServiceResponse>(serializedResponse)
        println(deserialization)
        val deserialization2 = Json.decodeFromString<List<Service>>("[{\"id\":\"id1\",\"type\":{\"_id\":\"a1\",\"type\":\"app\"}},{\"id\":\"id2\",\"type\":{\"_id\":\"a2\",\"type\":\"auto\"}},{\"id\":\"id4\",\"type\":{\"_id\":\"a4\",\"type\":\"delivery\"}}]")
        println(deserialization2)
    }
}