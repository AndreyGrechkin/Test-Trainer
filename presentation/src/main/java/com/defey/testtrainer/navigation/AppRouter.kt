package com.defey.testtrainer.navigation

interface AppRouter {
    fun navigateTo(screen: Screen)
    fun navigateBack()
    fun popBackStack()
    fun navigateToRoute(route: String)
}