@file:Suppress("NonAsciiCharacters", "FunctionName")

package quickStartKt

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File

//https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/basic-serialization.md

private fun main() {
//    val pr =Project("JORA")
//    val json = Json.encodeToJsonElement(pr)
//    println(json)
//    println(Json.decodeFromString(ListSerializer(StoredPositions.serializer()), "[]"))
    val nLUserSettingsUpdate = """
{
  "orderSettings": [
    {
      "ownerFirmId": 938,
      "expirationTime": {
        "count": 75599000
      }
    }
  ]
}
""".trimIndent()
//    val str = Json.encodeToString(nLUserSettingsUpdate)
//    println(str)
    val o = Json.decodeFromString<NLUserSettingsUpdate>(nLUserSettingsUpdate)
    println(o)
}

@Serializable
data class StoredPositions(
    val id: Int
)

private val file = File("src/main/kotlin/quickStartKt/MyJson.json")

private fun `сериализация в json, запись в файл, и десериализация из файла`() {
    file.writeText("")
    val project = Project("Kukaracha", "Assembler")
    val jsonString = Json.encodeToString(project)
    println(jsonString)

    file.writeText(jsonString)

    val decoded = Json.decodeFromString<Project>(file.readText())
    println(decoded)
}

@Serializable
internal data class Project(
    val name: String,
    val language: String = "Default not encode" //можно заставить encode значения по умолчанию только с @EncodeDefault
) {
    //не сериализуется
    val smComputedProp: String
        get() = name

    //не сериализуется
    val smDelegateProp by ::language

    //при десериализации будет вызван конструктор и блок инит
    init {
        println("I'm born")
    }
}

@Serializable
data class NLUserSettingsUpdate(
    val orderSettings: List<NLOrderSettings>
)

@Serializable
data class NLOrderSettings(
    val ownerFirmId: Long,
    val expirationTime: Instant?,
)
