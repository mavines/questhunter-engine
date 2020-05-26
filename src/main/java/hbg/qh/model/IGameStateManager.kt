package hbg.qh.model

interface IGameStateManager {
    fun saveFileName() : String
    fun initialState() : GameState
    fun tagToThing(tag: String) : Thing
    val name: String
    val iconResource: Int
}