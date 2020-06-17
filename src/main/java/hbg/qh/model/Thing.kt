package hbg.qh.model

import java.io.Serializable

interface Thing : Serializable {
    val id: String
    val name: String
    val description: String
    val imageResource: Int?
    val contextText: GameState.() -> String
    val obtained: Boolean
}

fun Thing.copy(
    id: String = this.id,
    name: String = this.name,
    description: String = this.description,
    imageResource: Int? = this.imageResource,
    text: (GameState) -> String = this.contextText,
    obtained: Boolean = this.obtained
): Thing {
    return when (this) {
        is Item -> this.copy(id = id, name = name, description = description, imageResource =  imageResource, contextText = text, obtained = obtained)
        is GameCharacter -> this.copy(id = id, name = name, description = description, imageResource =  imageResource, contextText = text, obtained = obtained)
        is Quest -> this.copy(id = id, name = name, description = description,imageResource =  imageResource,  contextText = text, obtained = obtained)
        else -> {
            throw Exception("Invalid Thing: $this")
        }
    }
}