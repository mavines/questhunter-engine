package hbg.qh.model

inline class Criteria(private val checker: (GameState) -> Boolean) : (GameState) -> Boolean {
    override fun invoke(state: GameState): Boolean =  checker.invoke(state)
}

inline class QuestResult(private val result: (GameState) -> GameState) : (GameState) -> GameState {
    override fun invoke(state: GameState): GameState =  result.invoke(state)
}

data class Quest(
    override val id: String,
    override val name: String,
    override val description: String,
    override val contextText: GameState.() -> String,
    internal val criteria: List<Criteria>,
    val results: List<QuestResult>,
    val steps: List<Quest> = emptyList(),
    override val obtained: Boolean = false,
    val completed: Boolean = false
) : Thing {
    fun canComplete(gameState: GameState): Boolean =
        criteria.map { it(gameState) }
            .reduce { acc, curr -> acc && curr }
}