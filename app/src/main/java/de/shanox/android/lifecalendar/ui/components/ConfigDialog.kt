package de.shanox.android.lifecalendar.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import de.shanox.android.lifecalendar.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ConfigDialog() {
    var open by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = open) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                open = false
            }
        ){
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = { Text(stringResource(id = R.string.config_title )) }
                    )
                }
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {

                }
            }
        }
    }
}
