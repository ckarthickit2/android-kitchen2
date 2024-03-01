package me.kartdroid.androidkitchen2.kotlinxserialization

import kotlinx.serialization.Serializable

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 16/11/23
 */
@Serializable
class ServiceResponse(val services: List<Service>)