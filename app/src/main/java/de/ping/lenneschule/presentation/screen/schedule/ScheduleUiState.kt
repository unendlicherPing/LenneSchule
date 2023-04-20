package de.ping.lenneschule.presentation.screen.schedule

import de.ping.lenneschule.domain.model.Schedule
import java.time.LocalDate

data class ScheduleUiState(

    val isLoading: Boolean = false,
    val currentDate: LocalDate = LocalDate.now(),
    val schedules: HashMap<LocalDate, Schedule> = hashMapOf(),
    val error: String = "",
)
