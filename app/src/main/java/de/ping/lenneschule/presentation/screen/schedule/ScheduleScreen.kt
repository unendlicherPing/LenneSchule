/*
    Copyright (C) 2023  Moritz Pollack

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.ping.lenneschule.presentation.screen.schedule

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.ping.lenneschule.presentation.screen.schedule.components.SubstitutionCard
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {

    val scheduleUiState = scheduleViewModel.scheduleUiState.collectAsState()

    val topAppBarState = rememberTopAppBarState()
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, day ->
            scheduleViewModel.setDate(year, month, day)
        },
        scheduleUiState.value.currentDate.year,
        scheduleUiState.value.currentDate.monthValue - 1,
        scheduleUiState.value.currentDate.dayOfMonth,
    )

    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            buttons = {},
            title = { Text(text = "Hint") },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    scheduleUiState.value.schedules[scheduleUiState.value.currentDate]?.let {
                        Text(
                            text = it.hint
                        )
                    }
                }
            })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.clickable {
                            datePickerDialog.show()
                        },
                        text = scheduleUiState.value.currentDate.format(
                            DateTimeFormatter.ofPattern(
                                "EEEE d. LLLL"
                            ),
                        ),
                    )
                },
                scrollBehavior = topAppBarScrollBehavior,
                actions = {
                    IconButton(onClick = { openDialog = true }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
                    }
                }
            )
        },
        modifier = Modifier
            .pointerInput(Unit) {
                var direction = 0
                detectHorizontalDragGestures(onHorizontalDrag = { change, dragAmount ->
                    change.consume()

                    when {
                        dragAmount > 0 -> direction = 0
                        dragAmount < 0 -> direction = 1
                    }
                }, onDragEnd = {
                    when (direction) {
                        0 -> scheduleViewModel.previousDay()
                        1 -> scheduleViewModel.nextDay()
                    }
                })
            }) {
        TransparentSystemBars()

        val pullRefreshState =
            rememberPullRefreshState(refreshing = scheduleUiState.value.isLoading, onRefresh = {
                scheduleViewModel.updateSchedule(scheduleUiState.value.currentDate)
            })

        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            ) {
                if (scheduleUiState.value.error.isNotEmpty()) {
                    item {
                        Text(
                            text = "Error: ${scheduleUiState.value.error}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                if (!scheduleUiState.value.isLoading) {
                    scheduleUiState.value.schedules[scheduleUiState.value.currentDate]?.schedule?.forEach { substitution ->
                        item {
                            SubstitutionCard(
                                substitution = substitution,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = scheduleUiState.value.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}


@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen()
}