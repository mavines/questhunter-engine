package hbg.nfc.model

data class Quest(
    override val id: String,
    override val name: String,
    override val description: String,
    override val contextText: GameState.() -> String,
    internal val criteria: List<GameState.() -> Boolean>,
    val results: List<GameState.() -> GameState>,
    val steps: List<Quest> = emptyList(),
    override val obtained: Boolean = false,
    val completed: Boolean = false
) : Thing {
    fun canComplete(gameState: GameState): Boolean =
        criteria.map { gameState.it() }
            .reduce { acc, curr -> acc && curr }
}