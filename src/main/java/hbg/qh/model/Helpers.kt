package hbg.qh.model

// Results
fun gain(key: String): GameState.() -> GameState = {this.updateThing(key) {this.things[key]!!.copy(obtained = true)} }
fun lose(key: String): GameState.() -> GameState = {this.updateThing(key) {this.things[key]!!.copy(obtained = false)} }

// Conditions)
fun encountering(key: String): GameState.() -> Boolean = {this.currentThingId == key}
fun obtained(key: String): GameState.() -> Boolean = {this.obtained(key)}
fun completed(key: String): GameState.() -> Boolean = {this.completed(key)}

