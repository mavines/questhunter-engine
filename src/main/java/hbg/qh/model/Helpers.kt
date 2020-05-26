package hbg.qh.model

// Results
fun gain(key: String): QuestResult = QuestResult{state -> state.updateThing(key) {this.things[key]!!.copy(obtained = true)} }
fun lose(key: String): QuestResult = QuestResult{state -> state.updateThing(key) {this.things[key]!!.copy(obtained = false)} }

// Conditions
fun encountering(key: String): Criteria = Criteria { it.currentThingId == key}
fun obtained(key: String): Criteria = Criteria { it.currentThingId == key}
fun completed(key: String): Criteria = Criteria { it.currentThingId == key}

