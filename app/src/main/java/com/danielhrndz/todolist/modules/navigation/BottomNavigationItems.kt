package com.danielhrndz.todolist.modules.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Screen1 : BottomNavigationItems(
        route = "Home",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Screen2 : BottomNavigationItems(
        route = "Add Note",
        title = "Add Note",
        icon = Icons.Outlined.AddCircle
    )
    object Screen3 : BottomNavigationItems(
        route = "Account",
        title = "Account",
        icon = Icons.Outlined.AccountCircle
    )
}