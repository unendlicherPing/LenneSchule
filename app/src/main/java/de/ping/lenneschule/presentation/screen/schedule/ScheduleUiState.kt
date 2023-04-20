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

import de.ping.lenneschule.domain.model.Schedule
import java.time.LocalDate

data class ScheduleUiState(

    val isLoading: Boolean = false,
    val currentDate: LocalDate = LocalDate.now(),
    val schedules: HashMap<LocalDate, Schedule> = hashMapOf(),
    val error: String = "",
)
