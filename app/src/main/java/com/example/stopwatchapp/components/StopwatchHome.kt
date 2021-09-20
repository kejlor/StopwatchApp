package com.example.stopwatchapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stopwatchapp.models.TimeEntry
import com.example.stopwatchapp.ui.theme.DarkBlue
import com.example.stopwatchapp.ui.theme.DarkPurple
import com.example.stopwatchapp.ui.theme.LightBlue
import com.example.stopwatchapp.ui.theme.LightPurple
import com.example.stopwatchapp.viewmodels.TimeEntryViewModel
import kotlinx.coroutines.delay
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun StopwatchView(
    timeEntries: List<TimeEntry>,
    mTimeEntryViewModel: TimeEntryViewModel
) {
    var currentTime by remember {
        mutableStateOf(0L)
    }
    var isTimerCounting by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerCounting) {
        if (isTimerCounting) {
            delay(100L)
            currentTime += 100L
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(200.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally), backgroundColor = LightBlue,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = getTimeStringFromDouble(currentTime / 1000L),
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { isTimerCounting = true },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "START")
            }
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { isTimerCounting = false },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "STOP")
            }
        }
        Row {
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { mTimeEntryViewModel.addTimeEntry(TimeEntry(currentTime)) },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "LAP")
            }
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = {
                    currentTime = 0L
                    isTimerCounting = true
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "RESTART")
            }
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { mTimeEntryViewModel.deleteTimeEntries() },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "CLEAR")
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .padding(20.dp),
            backgroundColor = DarkBlue,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
            LazyColumn(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = timeEntries, itemContent = {
                    Text(text = "Time: " + getTimeStringFromDouble(it.timeElapsed / 1000L))
                    Divider()
                })
            }
        }
    }
}

private fun getTimeStringFromDouble(time: Long): String {
    val hours = time % 86400 / 3600
    val minutes = time % 86400 % 3600 / 60
    val seconds = time % 86400 % 3600 % 60

    return makeTimeString(hours, minutes, seconds)
}

private fun makeTimeString(hour: Long, min: Long, sec: Long): String =
    String.format("%02d:%02d:%02d", hour, min, sec)