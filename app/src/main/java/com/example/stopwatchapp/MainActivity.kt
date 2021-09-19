package com.example.stopwatchapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatchapp.components.StopwatchView
import com.example.stopwatchapp.ui.theme.StopwatchAppTheme
import com.example.stopwatchapp.viewmodels.TimeEntryViewModel
import com.example.stopwatchapp.viewmodels.TodoViewModelFactory
import kotlin.time.ExperimentalTime

class MainActivity : ComponentActivity() {
    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val mTodoViewModel: TimeEntryViewModel = viewModel(
                factory = TodoViewModelFactory(context.applicationContext as Application)
            )

            val timeEntries = mTodoViewModel.readAllData.observeAsState(listOf()).value
            StopwatchAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    StopwatchView("test", timeEntries, mTodoViewModel)
                }
            }
        }
    }
}
