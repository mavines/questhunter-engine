package hbg.qh.model

import java.io.Serializable

interface Thing : Serializable {
    val id: String
    val name: String
    val description: String
    val contextText: GameState.() -> String
    val obtained: Boolean
}

fun Thing.copy(
    id: String = this.id,
    name: String = this.name,
    text: (GameState) -> String = this.contextText,
    obtained: Boolean = this.obtained
): Thing {
    return when (this) {
        is Item -> this.copy(id = id, name = name, contextText = text, obtained = obtained)
        is GameCharacter -> this.copy(id = id, name = name, contextText = text, obtained = obtained)
        is Quest -> this.copy(id = id, name = name, contextText = text, obtained = obtained)
        else -> {
            throw Exception("Invalid Thing: $this")
        }
    }
}