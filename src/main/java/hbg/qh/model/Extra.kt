package hbg.qh.model

data class Extra<T>(
    override val id: String,
    override val name: String,
    override val description: String,
    override val imageResource: Int? = null,
    override val contextText: GameState.() -> String,
    override val obtained: Boolean,
    val value: T
    ) : Thing {
}