package de.shanox.android.lifecalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.shanox.android.lifecalendar.ui.components.Calendar
import de.shanox.android.lifecalendar.ui.components.ConfigDialog
import de.shanox.android.lifecalendar.ui.theme.LifeCalendarTheme
import de.shanox.android.lifecalendar.utils.PreferenceManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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

    LifeCalendarTheme {
        Scaffold {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                Text(
                    text = stringResource(id = R.string.title),
                    fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp)
                )

                Calendar()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}