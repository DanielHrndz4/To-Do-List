package com.danielhrndz.todolist.component.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.model.ObjectClass
import com.danielhrndz.todolist.ui.theme.dark
import com.danielhrndz.todolist.viewmodel.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ObjectListComponent(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(true) }
    val data: MutableState<MutableList<ObjectClass>> = remember {
        mutableStateOf(mutableListOf())
    }
    val viewModel = DataViewModel()

    LaunchedEffect(true) {
        isLoading.value = true
        val startTime = System.currentTimeMillis()
        GlobalScope.launch(Dispatchers.IO) {
            val newData = viewModel.getData()
            val endTime = System.currentTimeMillis()
            val elapsedTime = endTime - startTime
            val remainingTime = maxOf(0L, 1500 - elapsedTime)
            if (remainingTime > 0) {
                Thread.sleep(remainingTime)
            }
            withContext(Dispatchers.Main) {
                isLoading.value = false
                if (newData.isNotEmpty()) {
                    isEmpty.value = false
                    data.value = newData
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(dark),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        } else {
            if (isEmpty.value) {
                WithoutNote(navController)
            } else {
                Text(
                    text = "All Task",
                    modifier = Modifier.padding(vertical = 20.dp),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    itemsIndexed(data.value) { index, item ->
                        EditTask(index = index, item = item, data = data)
                    }
                }
                AddButtom(navController)
            }
        }
    }
}
