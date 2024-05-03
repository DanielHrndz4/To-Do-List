package com.danielhrndz.todolist.modules.navigation

sealed class Routes(val route: String) {
    object Welcome : Routes("welcomeScreen")
}