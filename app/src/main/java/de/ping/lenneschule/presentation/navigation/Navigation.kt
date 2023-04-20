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
package de.ping.lenneschule.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.ping.lenneschule.presentation.screen.schedule.ScheduleScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navigationController = rememberNavController()

    Scaffold(
        bottomBar = {
/*            NavigationBar {
                val navigationBackStackEntry by navigationController.currentBackStackEntryAsState()
                val currentDestination = navigationBackStackEntry?.destination

                listOf(
                    Screen.Schedule
                ).forEach { screen ->
                    NavigationBarItem(icon = {
                        Icon(screen.icon, contentDescription = null)
                    },
                        label = {
                            Text(text = stringResource(id = screen.resourceId))
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navigationController.navigate(screen.route) {
                                popUpTo(navigationController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }*/
        },
    ) { innerPadding ->
        NavHost(
            navController = navigationController,
            startDestination = Screen.Schedule.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            /*composable(route = Screen.Timetable.route) {
                TimetableScreen {
                    navigationController.navigate(Screen.Profile.route) {
                        popUpTo(navigationController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }*/
            composable(route = Screen.Schedule.route) {
                ScheduleScreen()
            }/*composable(route = Screen.Profile.route) {
                ProfileScreen {
                    navigationController.navigate(Screen.Schedule.route) {
                        popUpTo(navigationController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }*/
        }
    }
}