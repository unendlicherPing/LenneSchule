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