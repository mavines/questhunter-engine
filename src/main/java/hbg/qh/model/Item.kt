package hbg.qh.model

data class Item(
    override val id: String,
    override val name: String,
    override val description: String,
    override val imageResource: Int? = null,
    val iconResource: Int,
    override val contextText: GameState.() -> String = { "" },
    override val obtained: Boolean = false
) : Thing