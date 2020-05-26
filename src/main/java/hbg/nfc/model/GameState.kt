package hbg.nfc.model

import java.io.Serializable

data class GameState(
    val currentThingId: String,
    val things: Map<String, Thing>
) : Serializable {
    val characters: Map<String, GameCharacter> =
        things.filter { it.value is GameCharacter } as Map<String, GameCharacter>
    val items: Map<String, Item> = things.filter { it.value is Item } as Map<String, Item>
    val quests: Map<String, Quest> = things.filter { it.value is Quest } as Map<String, Quest>
    val currentThing: Thing =
        things[currentThingId] ?: throw Exception("Invalid Current Thing: $currentThingId")

    fun completed(key: String) = quests[key]?.completed ?: false;
    fun obtained(key: String) = things[key]?.obtained ?: false;
    fun updateThings(updatedThings: Map<String, Thing>): GameState =
        this.copy(things = things.plus(updatedThings))

    fun updateThing(thingKey: String, update: GameState.(String) -> Thing): GameState =
        this.copy(things = things.plus(thingKey to update(thingKey)))
}