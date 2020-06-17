package hbg.qh.model

data class Event(
    override val id: String,
    override val name: String,
    override val description: String,
    override val imageResource: Int? = null,
    override val contextText: GameState.() -> String,
    internal val criteria: List<GameState.() -> Boolean>,
    val results: List<GameState.() -> GameState>,
    override val obtained: Boolean = true
) : Thing {
    fun triggers(gameState: GameState): Boolean =
        criteria.map { gameState.it() }
            .reduce { acc, curr -> acc && curr }
}