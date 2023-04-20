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
package de.ping.lenneschule.data.remote

import de.ping.lenneschule.data.remote.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface ScheduleApi {

    // TODO: make enums for task, format, order, and direction
    // TODO: make better date type
    @GET("/unterricht/vertretungsplan")
    suspend fun getSchedule(
        @Header("Cookie") cookie: String,
        @QueryMap token: Map<String, String>,
        @Query("task") task: String = "updateSchedule",
        @Query("format") format: String = "json",
        @Query("date") date: String = LocalDate.now().format(
            DateTimeFormatter.BASIC_ISO_DATE
        ),
        @Query("order") order: String = "class",
        @Query("dir") direction: String = "ASC"
    ): ScheduleDto
}