package me.kartdroid.androidkitchen2.kotlinxserialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 16/11/23
 */
@Serializable
class Service {
        var id: String = ""
        var type: ServiceId? = null
        constructor(id: String, type:ServiceId){
                this.id = id;
                this.type = type
        }
}

@Serializable
class ServiceId(
        @SerialName("_id") val id: String,
        val type: String
)