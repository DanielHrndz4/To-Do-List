package com.danielhrndz.todolist.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.danielhrndz.todolist.model.ObjectClass
import com.danielhrndz.todolist.modules.navigation.Routes
import com.danielhrndz.todolist.ui.theme.dark
import com.danielhrndz.todolist.ui.theme.lightGray
import com.danielhrndz.todolist.viewmodel.DataViewModel

@Composable
fun InsertDataComponent(navController: NavHostController) {
    val TextFieldTitle = rememberSaveable{ mutableStateOf("") }
    val TextFieldDescription = rememberSaveable{ mutableStateOf("") }
    val viewModel = DataViewModel()
    val myList = remember { mutableListOf<ObjectClass>() }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    Box(
        modifier = Modifier.clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)).background(lightGray)
    ){
        Column(modifier = Modifier.padding(vertical = 30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            //Text(text = "Add Task", color = Color.White, fontSize = 40.sp, modifier = Modifier.padding(bottom = 50.dp), fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = TextFieldTitle.value,
                onValueChange = { TextFieldTitle.value = it },
            )
            OutlinedTextField(
                label = { Text("Description Task") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 30.dp, vertical = 8.dp)
                    .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(8.dp)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = TextFieldDescription.value,
                onValueChange = { TextFieldDescription.value = it },
            )
            Button(modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 8.dp)
                .fillMaxWidth(),colors = ButtonDefaults.buttonColors(dark), shape = RoundedCornerShape(8.dp), onClick = {

                val newObject = ObjectClass(
                    title = TextFieldTitle.value,
                    description = TextFieldDescription.value
                )
                if (TextFieldTitle.value == "" || TextFieldTitle.value == ""){
                    Toast.makeText(context, "There is no data to enter", Toast.LENGTH_SHORT).show()
                }else{
                    myList.add(newObject)
                    Toast.makeText(context, "Data entered successfully!", Toast.LENGTH_SHORT).show()
                    viewModel.setData(myList)
                    navController.navigate("Home")
                }
            }) {
                Text(text = "Add Task", color = Color.White)
            }
        }
    }
}