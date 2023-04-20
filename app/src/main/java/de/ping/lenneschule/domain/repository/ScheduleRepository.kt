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