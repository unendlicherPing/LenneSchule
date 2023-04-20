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