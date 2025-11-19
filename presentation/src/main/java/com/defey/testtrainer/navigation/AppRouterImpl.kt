package com.defey.testtrainer.navigation

import androidx.navigation.NavHostController
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AppRouterImpl @Inject constructor() : AppRouter {

    private var navController: NavHostController? = null

    fun setNavController(controller: NavHostController) {
        navController = controller
    }

    fun clear() {
        navController = null
    }

    override fun navigateTo(screen: Screen) {
        val route = when (screen) {
            is Screen.Workout -> Routes.getWorkoutRoute(screen.id)
            Screen.LoadWorkout -> Routes.LOADING_WORKOUT

        }
        navController?.navigate(route)
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun navigateToRoute(route: String) {
        navController?.navigate(route)
    }
}