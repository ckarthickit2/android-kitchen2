package me.kartdroid.androidkitchen2.serializers

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


typealias SerializablePersistentList<T> = @Serializable(PersistentListSerializer::class) PersistentList<T>

@OptIn(ExperimentalSerializationApi::class)
@Suppress("EXTERNAL_SERIALIZER_USELESS")
@Serializer(forClass = PersistentList::class)
internal class PersistentListSerializer<T>(private val dataSerializer: KSerializer<T>) :
    KSerializer<PersistentList<T>> {
    private class PersistentListDescriptor : SerialDescriptor by serialDescriptor<List<String>>() {
        override val serialName: String = "kotlinx.serialization.immutable.PersistentList"
    }

    override val descriptor: SerialDescriptor = PersistentListDescriptor()
    override fun serialize(encoder: Encoder, value: PersistentList<T>) {
        return ListSerializer(dataSerializer).serialize(encoder, value.toList())
    }

    override fun deserialize(decoder: Decoder): PersistentList<T> {
        return ListSerializer(dataSerializer).deserialize(decoder).toPersistentList()
    }
}