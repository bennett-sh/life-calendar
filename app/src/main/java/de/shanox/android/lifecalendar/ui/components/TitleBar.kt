package de.shanox.android.lifecalendar.ui.components

import android.os.Bundle
import androidx.compose.ui.Alignment
import de.shanox.android.lifecalendar.R
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TitleBar() {
    SmallTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title),
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                fontSize = MaterialTheme.typography.displayLarge.fontSize,
                fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 7.5.dp)
            )
        },
        actions = {
            IconButton(
                onClick = {
                    // TODO: Config Page
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.config_title),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        modifier = Modifier.padding(top = 25.dp, start = 10.dp, end = 10.dp)
    )
}
