package com.msa.productcalculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.productcalculator.ui.theme.*

@Composable
internal fun InputDisplayComponent() {
    Box(
        Modifier
            .fillMaxWidth()
            .shadow(
                color = ResultShadowColorTop,
                blurRadius = 15.dp,
                offsetX = (-6).dp,
                offsetY = (-6).dp,
            )
            .shadow(
                color = ResultShadowColorBottom,
                blurRadius = 15.dp,
                offsetX = (6).dp,
                offsetY = (6).dp,
            )
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(
                vertical = MaterialTheme.spacing.md,
                horizontal = MaterialTheme.spacing.sm
            )
    ) {
        Column(

        ) {
            val buttons = listOf("KA", "EA")
            LazyRow(
                Modifier
                    .padding(10.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(25.dp),
            ) {
                items(buttons) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        Text(
                            text = it,
                            color = Color.White
                        )

                        Text(
                            text = "__",
                            color = Color.White
                        )

                    }


                }
            }



        }


    }
}

@Preview(showBackground = true)
@Composable
fun InputDisplayComponentPreview() {
    Box(modifier = Modifier.padding(10.dp)) {
        InputDisplayComponent()
    }

}

