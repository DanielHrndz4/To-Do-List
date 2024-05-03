package com.danielhrndz.todolist.modules.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.Account
import com.danielhrndz.todolist.AddNote
import com.danielhrndz.todolist.Home
import com.danielhrndz.todolist.Welcome

@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    NavHost(navController, startDestination = Routes.Welcome.route) {
        composable(Routes.Welcome.route) {
            onBottomBarVisibilityChanged(true)
            Home()
        }
        composable(BottomNavigationItems.Screen1.route) {
            onBottomBarVisibilityChanged(true)
            Home()
        }
        composable(BottomNavigationItems.Screen2.route) {
            onBottomBarVisibilityChanged(true)
            AddNote()
        }
        composable(BottomNavigationItems.Screen3.route) {
            onBottomBarVisibilityChanged(true)
            Account()
        }
    }
}