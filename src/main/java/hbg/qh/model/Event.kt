package hbg.qh.model

data class Event(
    override val id: String,
    override val name: String,
    override val description: String,
    override val contextText: GameState.() -> String,
    internal val criteria: List<Criteria>,
    val results: List<GameState.() -> GameState>,
    override val obtained: Boolean = true
) : Thing {
    fun triggers(gameState: GameState): Boolean =
        criteria.map { it(gameState) }
            .reduce { acc, curr -> acc && curr }
}