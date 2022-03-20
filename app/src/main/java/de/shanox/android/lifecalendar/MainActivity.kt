package de.shanox.android.lifecalendar

import android.util.Log
import android.os.Bundle
import java.time.LocalDate
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.shanox.android.lifecalendar.ui.components.TitleBar
import de.shanox.android.lifecalendar.ui.components.Calendar
import de.shanox.android.lifecalendar.ui.components.ConfigDialog
import de.shanox.android.lifecalendar.ui.theme.LifeCalendarTheme
import de.shanox.android.lifecalendar.utils.ConfigDialogManager
import de.shanox.android.lifecalendar.utils.PreferenceManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState

class MainActivity : AppCompatActivity() {

    companion object {
        public var activity: AppCompatActivity? = null

        public var colored = mutableStateOf(false)
        public var expectedAge = mutableStateOf(80)
        public var birthday: MutableState<LocalDate?> = mutableStateOf(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainActivity.activity = this

        val prefMgr = PreferenceManager(this)
        val exAge = prefMgr.getPreference("expectedAge")
        val colored = prefMgr.getPreference("colored")

        if(exAge is Int) {
            MainActivity.expectedAge.value = exAge
        }

        if(colored is Boolean) {
            MainActivity.colored.value = colored
        }

        MainActivity.birthday.value = prefMgr.getLocalDate("birthday") ?: LocalDate.of(2000, 1, 1)

        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val prefMgr = PreferenceManager(LocalContext.current)

    Log.i("ABC", "RERENDER")

    if(prefMgr.getPreference("birthday") == null) {
        ConfigDialogManager.getInstance().isVisible.value = true
    }

    LifeCalendarTheme {
        Scaffold(
            topBar = { TitleBar() }
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ConfigDialog()

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Calendar(
                        expectedAge = MainActivity.expectedAge.value as Int ?: 81,
                        birthday = MainActivity.birthday.value as LocalDate ?: LocalDate.of(2000, 1, 1),
                        colored = MainActivity.colored.value as Boolean ?: false
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}