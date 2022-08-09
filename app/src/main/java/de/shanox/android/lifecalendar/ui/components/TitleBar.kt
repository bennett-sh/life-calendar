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
import de.shanox.android.lifecalendar.ui.components.Title
import de.shanox.android.lifecalendar.utils.ConfigDialogManager
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar() {
    SmallTopAppBar(
        title = {
            Title(text = stringResource(id = R.string.title))
        },
        actions = {
            IconButton(
                onClick = {
                    ConfigDialogManager.getInstance().isVisible.value = true
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
