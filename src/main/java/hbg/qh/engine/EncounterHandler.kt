package hbg.qh.engine

import hbg.qh.model.GameState
import hbg.qh.model.Quest
import hbg.qh.model.Thing

fun updateState(startState: GameState, encounteredThing: Thing): GameState {
    val stateWithEncounter = startState.copy(currentThingId = encounteredThing.id)

    val updatedQuests = updateQuests(stateWithEncounter)

    val stateWithCompletedQuests = stateWithEncounter.updateThings(updatedQuests)

    val questResults = questResults(startState, updatedQuests)

    return applyResults(stateWithCompletedQuests, questResults)
}

private fun applyResults(
    stateWithCompletedQuests: GameState,
    results: List<GameState.() -> GameState>
): GameState =
    results.fold(stateWithCompletedQuests) { state, result -> state.result() }
    

private fun questResults(
    startState: GameState,
    updatedQuests: Map<String, Quest>
): List<GameState.() -> GameState> =
    startState.quests.filter { (id, quest) ->
        !quest.completed && updatedQuests[id]!!.completed
    }.flatMap { it.value.results }


private fun updateQuests(stateWithEncounter: GameState): Map<String, Quest> =
    stateWithEncounter.quests.mapValues { (_, quest) ->
        updateQuest(
            quest,
            stateWithEncounter
        )
    }

fun updateQuest(quest: Quest, gameState: GameState): Quest {
    val updatedSteps = quest.steps.map { step ->
        if (step.canComplete(gameState)) {
            step.copy(completed = true)
        } else {
            step
        }
    }

    val questCompleted = quest.completed || quest.canComplete(gameState)

    return quest.copy(steps = updatedSteps, completed = questCompleted)
}
