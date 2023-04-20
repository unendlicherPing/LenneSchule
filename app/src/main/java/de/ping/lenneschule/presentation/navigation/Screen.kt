package de.ping.lenneschule.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import de.ping.lenneschule.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {

    object Timetable : Screen(route = "timetable", Icons.Default.Home, R.string.timetable)
    object Schedule : Screen(route = "schedule", Icons.Default.List, R.string.schedule)
    object Profile : Screen(route = "profile", Icons.Default.AccountCircle, R.string.profile)
}
