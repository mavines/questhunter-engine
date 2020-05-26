package hbg.nfc.model

data class Extra<T>(
    override val id: String,
    override val name: String,
    override val description: String,
    override val contextText: GameState.() -> String,
    override val obtained: Boolean,
    val value: T
    ) : Thing {
}