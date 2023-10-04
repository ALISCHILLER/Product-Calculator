package com.msa.productcalculator

import androidx.lifecycle.ViewModel
import com.msa.productcalculator.model.StateModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class CalculatorViewModel : ViewModel() {

    val state: MutableStateFlow<StateModel> = MutableStateFlow(StateModel())

    internal val viewState = state
        .map {
            val num = it.num.ifEmpty { "0" }
            val unitId = it.unitId.ifEmpty { "1" }

            ViewState(num, unitId)
        }

    fun dispatch(action: ActionType) {
        when (action) {
            is ActionType.Number -> onNumberClicked(action.number)
            is ActionType.Delete -> onDeleteClicked()
            is ActionType.close -> onClearClicked()
            else -> {

            }
        }
    }


    private fun onNumberClicked(number: Int) {
        val currentState = state.value
        state.value = currentState.copy(num = currentState.num + number)
    }

    private fun onDeleteClicked() {
        val currentState = state.value
        state.value = currentState.copy(num = currentState.num.dropLast(1))

    }

    private fun onClearClicked() {
        val currentState = state.value
        state.value = currentState.copy(num = "0")

    }
    internal class ViewState(val num: String,val UnitId: String)
}