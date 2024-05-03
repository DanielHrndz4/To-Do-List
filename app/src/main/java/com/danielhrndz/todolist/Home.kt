package com.danielhrndz.todolist

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielhrndz.todolist.model.ObjectClass
import com.danielhrndz.todolist.ui.theme.dark
import com.danielhrndz.todolist.ui.theme.lightGray
import com.danielhrndz.todolist.viewmodel.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview
@Composable
fun Home() {
    ObjectListComponent(modifier = Modifier)
}

@Composable
fun WithoutNote(){
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
        Row(

        ) {

        }
    }
}

@Composable
fun ObjectListComponent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(true) }
    val data: MutableState<MutableList<ObjectClass>> = remember {
        mutableStateOf(mutableListOf())
    }
    val viewModel = DataViewModel()
    var editingText1 by remember { mutableStateOf(false) }
    var editedText1 by remember { mutableStateOf("") }
    var editingText2 by remember { mutableStateOf(false) }
    var editedText2 by remember { mutableStateOf("") }

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
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
        } else {
            if(isEmpty.value){
                WithoutNote()
            }else{
                Text(
                    text = "All Task",
                    modifier = Modifier
                        .padding(vertical = 20.dp),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                ) {
                    itemsIndexed(data.value) { index, item ->
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 20.dp, horizontal = 10.dp)
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = lightGray)
                            ){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 5.dp,
                                            end = 5.dp,
                                            top = 2.dp,
                                            bottom = 2.dp
                                        ),
                                ){
                                    if (editingText1) {
                                        TextField(
                                            value = editedText1,
                                            onValueChange = { editedText1 = it },
                                            singleLine = true,
                                            modifier = Modifier
                                                .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 5.dp)
                                                .fillMaxWidth(),
                                            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White),
                                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                            keyboardActions = KeyboardActions(
                                                onDone = {
                                                    item.PropertyOne = editedText1
                                                    editingText1 = false
                                                }
                                            )
                                        )
                                    } else if (editingText2) {
                                        TextField(
                                            value = editedText2,
                                            onValueChange = { editedText2 = it },
                                            singleLine = true,
                                            modifier = Modifier
                                                .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 5.dp)
                                                .fillMaxWidth(),
                                            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White),
                                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                            keyboardActions = KeyboardActions(
                                                onDone = {
                                                    item.PropertyTwo = editedText2
                                                    editingText2 = false
                                                }
                                            )
                                        )
                                    } else {
                                        Text(
                                            text = item.PropertyOne,
                                            modifier = Modifier
                                                .clickable {
                                                    editedText1 = data.value[index].PropertyOne
                                                    editingText1 = true
                                                }
                                                .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 5.dp)
                                                .fillMaxWidth(),
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.White
                                        )
                                        Text(
                                            text = item.PropertyTwo,
                                            Modifier
                                                .clickable {
                                                    editedText2 = data.value[index].PropertyTwo
                                                    editingText2 = true
                                                }
                                                .padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
                                                .fillMaxWidth(),
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultFloatingActionButtonExample() {
    FloatingActionButton(onClick = { /* Tus acciones */ }, shape = CircleShape) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Crear nota")
    }
}

@Composable
fun MiniFloatingActionButtonExample() {
    val miniFabSize = 40.dp
    FloatingActionButton(
        onClick = { /* Tus acciones */ },
        modifier = Modifier.size(miniFabSize)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Crear nota")
    }
}