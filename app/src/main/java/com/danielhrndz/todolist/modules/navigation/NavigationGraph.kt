package com.danielhrndz.todolist.modules.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.Account
import com.danielhrndz.todolist.Home

@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    NavHost(navController, startDestination = Routes.Welcome.route) {
        composable(Routes.Welcome.route) {
            onBottomBarVisibilityChanged(true)
            Home(navController)
        }
        composable(BottomNavigationItems.Screen1.route) {
            onBottomBarVisibilityChanged(true)
            Home(navController)
        }
        composable(BottomNavigationItems.Screen3.route) {
            onBottomBarVisibilityChanged(true)
            Account()
        }
    }
}