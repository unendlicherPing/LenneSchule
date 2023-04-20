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
package de.ping.lenneschule.data.repository

import de.ping.lenneschule.data.remote.ScheduleApi
import de.ping.lenneschule.data.remote.dto.ScheduleDto
import de.ping.lenneschule.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRepository {

    override suspend fun getSchedule(
        cookie: String,
        token: String,
        task: String,
        format: String,
        date: String,
        order: String,
        direction: String
    ): ScheduleDto =
        scheduleApi.getSchedule(
            cookie,
            mapOf(Pair(token, "1")),
            task,
            format,
            date,
            order,
            direction
        )
}