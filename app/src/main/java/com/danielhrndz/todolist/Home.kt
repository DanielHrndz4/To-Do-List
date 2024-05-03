package com.danielhrndz.todolist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.component.home.ObjectListComponent

@Composable
fun Home(navController: NavHostController) {
    ObjectListComponent(modifier = Modifier, navController)
}