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
package de.ping.lenneschule.domain.model

data class Substitution(

    val block: String,
    val `class`: String,
    val date: String,
    val hints: String,
    val important: String,
    val room: String,
    val substitutionRoom: String,
    val teacher: Teacher,
    val substitutionTeacher: Teacher,
    val subject: Subject,
    val substitutionSubject: Subject,
)
