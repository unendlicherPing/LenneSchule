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
