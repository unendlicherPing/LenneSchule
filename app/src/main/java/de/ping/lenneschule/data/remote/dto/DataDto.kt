package de.ping.lenneschule.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DataDto(

    val hint: String,
    val schedule: List<SubstitutionDto>,
    @SerializedName("schedule_enabled")
    val schedule_enabled: Boolean,
    @SerializedName("schedule_sum")
    val scheduleSum: String,
    val supervision: List<Any>,
    @SerializedName("supervision_enabled")
    val supervisionEnabled: Boolean,
    @SerializedName("supervision_sum")
    val supervisionSum: String
)