package com.defey.testtrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.defey.testtrainer.navigation.AppRouterImpl
import com.defey.testtrainer.navigation.Routes
import com.defey.testtrainer.ui.loadWorkout.LoadWorkoutScreen
import com.defey.testtrainer.ui.theme.TestTrainerTheme
import com.defey.testtrainer.ui.workout.WorkoutNavArgs
import com.defey.testtrainer.ui.workout.WorkoutScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appRouter: AppRouterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTrainerTheme {
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    appRouter.setNavController(navController)
                }

                DisposableEffect(Unit) {
                    onDispose {
                        appRouter.clear()
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = Routes.LOADING_WORKOUT
                ) {
                    composable(Routes.LOADING_WORKOUT) {
                        LoadWorkoutScreen()
                    }

                    composable(
                        route = Routes.WORKOUT,
                        arguments = listOf(navArgument(WorkoutNavArgs.WORKOUT_ID_ARG) {
                            type = NavType.StringType
                        })
                    ) {
                        WorkoutScreen()
                    }
                }

            }
        }
    }
}