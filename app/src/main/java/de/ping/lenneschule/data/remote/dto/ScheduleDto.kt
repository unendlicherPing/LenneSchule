package de.ping.lenneschule.data.remote.dto

import com.google.gson.annotations.SerializedName
import de.ping.lenneschule.domain.model.Schedule
import de.ping.lenneschule.domain.model.Subject
import de.ping.lenneschule.domain.model.Substitution
import de.ping.lenneschule.domain.model.Teacher

data class ScheduleDto(

    @SerializedName("data")
    val scheduleData: DataDto,
    val message: Any,
    val messages: Any,
    val success: Boolean
)

fun ScheduleDto.toSchedule(): Schedule {
    return Schedule(
        hint = scheduleData.hint,
        schedule = scheduleData.schedule.map {
            Substitution(
                block = it.block,
                `class` = it.classLong,
                date = it.date,
                hints = it.hints,
                important = it.important,
                room = it.roomToken,
                substitutionRoom = it.subRoomToken,
                teacher = Teacher(
                    name = it.teacher,
                    forename = it.teacherForename,
                    surname = it.teacherSurname,
                    token = it.teacherToken
                ),
                substitutionTeacher = Teacher(
                    name = it.subTeacher,
                    forename = it.subTeacherForename,
                    surname = it.subTeacherSurname,
                    token = it.subTeacherToken
                ),
                subject = Subject(name = it.subjectLong, token = it.subjectToken),
                substitutionSubject = Subject(name = it.subSubjectLong, token = it.subSubjectToken),
            )
        }
    )
}