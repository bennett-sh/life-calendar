package de.shanox.android.lifecalendar.ui.components

import android.util.Log
import java.time.LocalDate
import java.time.format.FormatStyle
import java.time.format.DateTimeFormatter

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.*

import de.shanox.android.lifecalendar.utils.ConfigDialogManager
import de.shanox.android.lifecalendar.utils.PreferenceManager

import de.shanox.android.lifecalendar.ui.components.DatePicker
import de.shanox.android.lifecalendar.ui.components.Title

import de.shanox.android.lifecalendar.MainActivity
import de.shanox.android.lifecalendar.R

fun Save(
    birthday: LocalDate,
    expectedAge: Int?,
    colored: Boolean?,
    prefMgr: PreferenceManager
) {
    prefMgr.storeLocalDate("birthday", birthday)
    MainActivity.birthday.value = birthday

    if(expectedAge != null) {
        prefMgr.storePreference("expectedAge", expectedAge)
        MainActivity.expectedAge.value = expectedAge
    }

    if(colored != null) {
        prefMgr.storePreference("colored", colored)
        MainActivity.colored.value = colored
    }

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ConfigDialog() {
    val prefMgr = PreferenceManager(LocalContext.current)

    var coloredPref by remember { mutableStateOf(prefMgr.getPreference("colored")) }

    var datePicked : LocalDate by remember { mutableStateOf(prefMgr.getLocalDate("birthday") ?: LocalDate.now()) }
    var expectedAge: String by remember { mutableStateOf((prefMgr.getPreference("expectedAge") ?: 81).toString())}
    var colored: Boolean by remember { mutableStateOf(false) }

    if(coloredPref != null) colored = coloredPref as Boolean

    val updatedDate = { date: LocalDate? ->
        datePicked = date ?: LocalDate.of(2000, 1, 1)
    }

    AnimatedVisibility(visible = ConfigDialogManager.getInstance().isVisible.value) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                ConfigDialogManager.getInstance().isVisible.value = false
            }
        ){
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = { Title(stringResource(id = R.string.config_title )) },
                        navigationIcon = {
                            IconButton(
                                onClick = {

                                    ConfigDialogManager.getInstance().isVisible.value = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = stringResource(R.string.config_title),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        },
                        modifier = Modifier.padding(top = 25.dp, start = 10.dp, end = 10.dp)
                    )
                }
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp)
                ) {
                    Column {

                        Text(
                            text = stringResource(R.string.birthday),
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                        )

                        DatePicker(
                            datePicked
                                .format(
                                    DateTimeFormatter
                                        .ofLocalizedDate(FormatStyle.LONG)
                                ),
                            updatedDate
                        )

                        Spacer(Modifier.height(50.dp))

                        Text(
                            text = stringResource(R.string.expected_age),
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                        )

                        Spacer(Modifier.height(10.dp))

                        BasicTextField(
                            value = expectedAge,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                expectedAge = it
                            },
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            decorationBox = { innerTextField ->
                                Row(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    innerTextField()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(50.dp))
                        )

                        Spacer(Modifier.height(50.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = colored,
                                onCheckedChange = {
                                    colored = it
                                }
                            )

                            Text(
                                text = stringResource(R.string.colored),
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                            )
                        }

                        Spacer(Modifier.height(50.dp))

                        Button({
                            Save(
                                datePicked,
                                expectedAge.toString().toInt(),
                                colored,
                                prefMgr
                            )

                            ConfigDialogManager.getInstance().isVisible.value = false
                        }) {
                            Row {
                                Text(stringResource(R.string.save))
                            }
                        }

                    }
                }
            }
        }
    }
}
