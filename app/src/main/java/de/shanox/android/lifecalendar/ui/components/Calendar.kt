package de.shanox.android.lifecalendar.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import java.time.LocalDate
import java.time.Period
import kotlin.math.floor
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import java.time.temporal.ChronoUnit

fun getColorByAge(
    age: Int
): Color {
    return (when(true) {
        age < 14 -> Color(6822077) // Purple
        age < 20 -> Color.Yellow
        age < 31 -> Color.Green
        age < 66 -> Color.Blue
        else -> Color.Red
    }).copy(alpha = 0.2f)
}

@Composable
fun Calendar(
    expectedAge: Int = 80,
    birthday: LocalDate = LocalDate.of(2000, 1, 1),
    filledColor: Color = MaterialTheme.colorScheme.primary,
    colored: Boolean = false
) {

    val filledWeeks = floor(ChronoUnit.WEEKS.between(birthday, LocalDate.now()).toDouble())
    val expectedWeeks = expectedAge * 52

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            modifier = Modifier
                .fillMaxWidth(0.93f)
        ) {
            (1..expectedWeeks).forEach {
                Surface(
                    color = if(colored) getColorByAge(it / 52) else Color.Transparent,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(1.dp)
                        .size(5.dp)
                ) {
                    Surface(
                        color = if(it > filledWeeks)
                                    if(colored)
                                        Color.Transparent
                                    else Color.Gray.copy(alpha = 0.2f)
                                else filledColor,
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .padding(0.2.dp)
                            .size(5.dp),

                        content = {}
                    )
                }
            }
        }
    }

}
