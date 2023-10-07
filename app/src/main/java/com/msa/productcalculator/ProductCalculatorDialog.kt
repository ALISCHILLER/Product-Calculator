package com.msa.productcalculator

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msa.productcalculator.model.StateModel
import com.msa.productcalculator.model.UnitProductModel
import com.msa.productcalculator.ui.screen.CalcButtonComponent
import com.msa.productcalculator.ui.screen.InputDisplayComponent
import com.msa.productcalculator.ui.theme.spacing


@Composable
fun ProductCalculatorDialog(
    unit:List<UnitProductModel>
) {
    val viewModel= viewModel<CalculatorViewModel>()

//    var unitProduct = remember { mutableStateListOf<UnitProductModel>()}
//    unitProduct= unit as SnapshotStateList<UnitProductModel>


    val state =
        viewModel.viewState.collectAsState(initial = CalculatorViewModel.ViewState("0","1"))
            .value

    val untiProduct =
        viewModel.viewUnit.collectAsState(initial = CalculatorViewModel.ViewUnit(unit))
            .value



    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxSize()

        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(color = Color.Black),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                ProductCalculatorScreen(untiProduct){
                    viewModel.dispatch(it)
                }
            }
        }
    }

}




@Composable
private fun ProductCalculatorScreen(
    unti: CalculatorViewModel.ViewUnit,
    dispatcher: (StateModel) -> Unit,

){
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .animateContentSize()
            .padding(5.dp)
            .padding(5.dp)
    ) {
        InputDisplayComponent(unti){

        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))
        Row(
            Modifier
                .padding(5.dp)
                .padding(5.dp)
        ) {
            CalcButtonsGridLayout(
                dispatcher = {dispatcher}
            )
        }

    }

}


@Composable
private fun CalcButtonsGridLayout(
    dispatcher: (ActionType) -> Unit
    ) {
    val buttons = listOf(
        ActionType.Number(7),
        ActionType.Number(8),
        ActionType.Number(9),
        ActionType.close,
        ActionType.Number(4),
        ActionType.Number(5),
        ActionType.Number(6),
        ActionType.confirm,
        ActionType.Number(3),
        ActionType.Number(2),
        ActionType.Number(1),
        ActionType.Decimal,
        ActionType.Number(0),
        ActionType.Delete,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        content = {
            items(buttons) {
                CalcButtonComponent(
                    modifier = Modifier.aspectRatio(1f),
                    color = it.buttonColor,
                    symbol = it.symbol
                ) {
                    dispatcher(it)
                }
            }
        }
    )

}


@Composable
@Preview
fun ProductCalculatorDialogPreview(

) {
    val unit = listOf(
        UnitProductModel("1","KA",true)
        ,
        UnitProductModel("2","EA",false)
    )
    ProductCalculatorDialog(unit)

}