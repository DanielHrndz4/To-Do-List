package com.danielhrndz.todolist.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.AddNote

@Composable
fun AddButtom(navController: NavHostController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .background(Color.Transparent),
    ) {
        AddNote(navController = navController)
    }
}