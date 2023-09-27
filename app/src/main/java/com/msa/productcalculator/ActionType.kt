package com.msa.productcalculator

import androidx.compose.ui.graphics.Color
import com.msa.productcalculator.ui.theme.*

sealed class ActionType(val symbol: String, val buttonColor: Color) {
    data class Number(val number: Int) : ActionType(number.toString(), ButtonBlue)
    data class Operator(val operation: Operators) : ActionType(operation.symbol, ButtonPink)

    object Calculate : ActionType("=", ButtonYellow)
    object Delete : ActionType("⇽", ButtonBlue)
    object Clear : ActionType("AC", ButtonPink)
    object Decimal : ActionType(".", ButtonBlue)
    object Percentage: ActionType("%", ButtonPink)
    object close: ActionType("X", ButtonPink)
    object confirm: ActionType("true", ButtonPink)
}

sealed class Operators(val symbol: String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("x")
    object Divide : Operators("÷")
    object Power : Operators("^")
}