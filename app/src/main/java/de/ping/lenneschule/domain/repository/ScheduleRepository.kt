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
package de.ping.lenneschule.domain.repository

import de.ping.lenneschule.data.remote.dto.ScheduleDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface ScheduleRepository {

    suspend fun getSchedule(
        cookie: String,
        token: String,
        task: String = "updateSchedule",
        format: String = "json",
        date: String = LocalDate.now().format(
            DateTimeFormatter.BASIC_ISO_DATE
        ),
        order: String = "class",
        direction: String = "ASC"
    ): ScheduleDto
}