package serializationKotlin

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlin.time.Clock
import kotlin.time.measureTime


@Serializable
data class NetworkMessage(
    val type: String,
    val protoVersion: Long,
    val msg: String,
    val sendTime: Instant,
)

@Serializable
data class PingRequestMessage(
    val type: Long,
    val protoVersion: Long,
    val msg: String,
    val sendTime: Instant,
)

@Serializable
data class Type(
    val type: String,
)

fun main() {
    val jsonP = Json { ignoreUnknownKeys = true }

    val networkMessage = NetworkMessage("AAA", 11L, "BBB", Clock.System.now())
    val json = jsonP.encodeToString(networkMessage)

    val oneField = measureTime {
        val des1 = jsonP.decodeFromString<Type>(json)
        println(des1)
    }

    val fourField = measureTime {
        val des2 = runCatching { jsonP.decodeFromString<PingRequestMessage>(json) }
        println(des2)
    }

    println(oneField)
    println(fourField)
}

@Serializable
data object SerializationVersion {
    val libraryVersion: String = "1.0.0"
}

@Serializable
data class Account(
    val code: String?,
)

@Serializable
data class AccountUpdate(
    val accounts: List<@Serializable(AccountCustomSerializer::class) Account>,
)

object AccountCustomSerializer : KSerializer<Account> {
    override val descriptor: SerialDescriptor = Account.serializer().descriptor

    override fun deserialize(decoder: Decoder): Account {
        println("Kek deserialize")
        return Account.serializer().deserialize(decoder)
    }

    override fun serialize(encoder: Encoder, value: Account) {
        println("Kek serialize")
        return Account.serializer().serialize(encoder, value)
    }

}