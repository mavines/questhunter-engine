package hbg.qh.model

data class GameCharacter(
    override val id: String,
    override val name: String,
    override val description: String,
    override val contextText: GameState.() -> String,
    override val obtained: Boolean = false
) : Thing;