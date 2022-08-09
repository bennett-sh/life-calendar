package de.shanox.android.lifecalendar.ui.components

import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Title(text: String) {
    Text(
        text = text,
        fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
        fontSize = MaterialTheme.typography.displayMedium.fontSize,
        fontWeight = MaterialTheme.typography.displayMedium.fontWeight,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(top = 7.5.dp)
    )
}
