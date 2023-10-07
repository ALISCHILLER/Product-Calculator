package com.msa.productcalculator

import androidx.lifecycle.ViewModel
import com.msa.productcalculator.model.StateModel
import com.msa.productcalculator.model.UnitProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class CalculatorViewModel : ViewModel() {

    val state: MutableStateFlow<StateModel> = MutableStateFlow(StateModel())
    val unit: MutableStateFlow<UnitProductModel> = MutableStateFlow(UnitProductModel())

    internal val viewState = state
        .map {
            val num = it.num.ifEmpty { "0" }
            val unitId = it.unitId.ifEmpty { "1" }

            ViewState(num, unitId)
        }

    internal val viewUnit = unit
        .map {
            val id=it.id.ifEmpty { "0" }
            val unitName=it.unitName.ifEmpty { "EA" }
            var unitNum=it.unitNum.ifEmpty { "0" }

            ViewUnit(listOf(it))
        }

    fun dispatch(action: StateModel) {
        when (action.actionType) {
            is ActionType.Number -> onNumberClicked(action.actionType.number)
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
    internal class ViewUnit(val unitProduct: List<UnitProductModel>)
}