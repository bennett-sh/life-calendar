package de.shanox.android.lifecalendar.ui.components

import java.util.Date
import java.time.ZoneId
import java.time.LocalDate

import androidx.compose.runtime.Composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.*

import com.google.android.material.datepicker.MaterialDatePicker
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.*

import de.shanox.android.lifecalendar.MainActivity
import de.shanox.android.lifecalendar.R

fun convertDateToLocalDateViaInstant(date: Date): LocalDate {
    return date.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate()
}

@Composable
fun DatePicker(
    date: String?,
    updateDate: (date: LocalDate?) -> Unit,
) {
    val activity = MainActivity.activity

    if(activity == null) {
        throw Exception("Activity is null")
    }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .padding(top = 10.dp)
            .border(0.5.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(50.dp))
            .clickable {
                val picker = MaterialDatePicker.Builder.datePicker().build()

                picker.show(activity.supportFragmentManager, picker.toString())
                picker.addOnPositiveButtonClickListener {
                    updateDate(convertDateToLocalDateViaInstant(Date(it)))
                }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
        ) {

            Text(
                text = date ?: stringResource(R.string.date_picker),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 20.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )

        }
    }
}
