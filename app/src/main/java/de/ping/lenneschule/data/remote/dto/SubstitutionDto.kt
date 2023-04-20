package de.ping.lenneschule.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SubstitutionDto(

    val block: String,
    @SerializedName("class_long")
    val classLong: String,
    @SerializedName("class_order")
    val classOrder: String,
    @SerializedName("class_token")
    val classToken: String,
    val date: String,
    val hints: String,
    @SerializedName("hl_room")
    val hlRoom: Boolean,
    val hour: String,
    val important: String,
    @SerializedName("room_token")
    val roomToken: String,
    @SerializedName("sub_room_long")
    val subRoomLong: String,
    @SerializedName("sub_room_token")
    val subRoomToken: String,
    @SerializedName("sub_subject_long")
    val subSubjectLong: String,
    @SerializedName("sub_subject_token")
    val subSubjectToken: String,
    @SerializedName("sub_teacher")
    val subTeacher: String,
    @SerializedName("sub_teacher_forename")
    val subTeacherForename: String,
    @SerializedName("sub_teacher_surname")
    val subTeacherSurname: String,
    @SerializedName("sub_teacher_token")
    val subTeacherToken: String,
    @SerializedName("subject_long")
    val subjectLong: String,
    @SerializedName("subject_token")
    val subjectToken: String,
    val teacher: String,
    @SerializedName("teacher_forename")
    val teacherForename: String,
    @SerializedName("teacher_surname")
    val teacherSurname: String,
    @SerializedName("teacher_token")
    val teacherToken: String
)