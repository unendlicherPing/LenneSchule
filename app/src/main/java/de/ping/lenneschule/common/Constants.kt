package de.ping.lenneschule.common

import android.net.Uri

object Constants {

    const val BASE_URL = "https://lenne-schule.de/"

    val scheduleUrl =
        Uri.Builder().scheme("https").authority("lenne-schule.de").appendPath("unterricht")
            .appendPath("vertretungsplan").build().toString()
}