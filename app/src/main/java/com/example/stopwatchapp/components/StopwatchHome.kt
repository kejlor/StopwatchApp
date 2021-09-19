package com.example.stopwatchapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun StopwatchView(
    text: String,
    timeEntries: List<TimeEntry>,
    mTimeEntryViewModel: TimeEntryViewModel
) {
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
                Text(text = "test", textAlign = TextAlign.Center)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "START")
            }
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { },
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
                onClick = { /*mTimeEntryViewModel.addTimeEntry(tim)*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightPurple),
                shape = CircleShape
            ) {
                Text(text = "LAP")
            }
            Button(
                modifier = Modifier
                    .requiredWidth(140.dp)
                    .padding(10.dp),
                onClick = { },
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
//            LazyColumn(content = )
        }
    }
}