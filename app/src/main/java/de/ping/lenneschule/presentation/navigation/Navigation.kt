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
            }
            /*composable(route = Screen.Profile.route) {
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