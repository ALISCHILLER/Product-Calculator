package com.msa.productcalculator.model

import com.msa.productcalculator.ActionType

data class StateModel(
    val num: String = "",
    val unitId: String=" ",
    val actionType: ActionType=ActionType.confirm
)