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