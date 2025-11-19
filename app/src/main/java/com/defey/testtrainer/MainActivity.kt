package com.defey.testtrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
import kotlin.text.clear

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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )



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
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            WorkoutScreen()
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTrainerTheme {
        Greeting("Android")
    }
}