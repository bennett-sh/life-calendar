package de.shanox.android.lifecalendar.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import de.shanox.android.lifecalendar.R

val Rota = FontFamily(
    Font(R.font.rota_bold)
)

val displayFont: FontFamily = Rota

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = Typography().displayLarge.copy(fontFamily = displayFont),
    displayMedium = Typography().displayMedium.copy(fontFamily = displayFont),
    displaySmall = Typography().displaySmall.copy(fontFamily = displayFont)
)