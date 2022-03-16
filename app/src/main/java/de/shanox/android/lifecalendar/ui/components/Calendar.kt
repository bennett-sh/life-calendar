package de.shanox.android.lifecalendar.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.Period
import kotlin.math.floor

@Composable
fun Calendar(
    expectedAge: Int = 80,
    birthday: LocalDate = LocalDate.of(2022, 3, 14),
    filledColor: Color = MaterialTheme.colorScheme.primary
) {

    val filledWeeks = floor((Period.between(birthday, LocalDate.now()).days/7).toDouble())
    val expectedWeeks = expectedAge * 52

    Canvas(
        modifier = Modifier
    ) {
        (1..expectedWeeks.toInt()).forEach {
            Log.i("CalRen", it.toString())
            var color = filledColor

            if(it > filledWeeks) {
                color = Color.Gray
            }

            drawRoundRect(
                color = color,
                cornerRadius = CornerRadius(x = 1f, y = 1f),
                topLeft = Offset(x = 1.5f, y = 1.5f)
            )
        }
    }

}
