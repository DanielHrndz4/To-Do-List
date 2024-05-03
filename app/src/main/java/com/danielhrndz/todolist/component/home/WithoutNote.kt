package com.danielhrndz.todolist.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.AddNote
import com.danielhrndz.todolist.R

@Composable
fun WithoutNote(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.todolist),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(250.dp)
        )
        Text(text = "What do you want to do today?", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "Tap + to add your tasks", color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(top=5.dp))
        Box(
            modifier = Modifier
                .padding(vertical = 30.dp)
        ){
            AddNote(navController = navController)
        }
    }
}