package com.danielhrndz.todolist.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielhrndz.todolist.model.ObjectClass
import com.danielhrndz.todolist.ui.theme.lightGray
import com.danielhrndz.todolist.viewmodel.DataViewModel

@Preview
@Composable
fun InsertDataComponent(){
    val TextFieldTitle = rememberSaveable{ mutableStateOf("") }
    val TextFieldDescription = rememberSaveable{ mutableStateOf("") }
    val viewModel = DataViewModel()
    val myList = remember { mutableListOf<ObjectClass>() }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(text = "Add Task", color = Color.White, fontSize = 40.sp, modifier = Modifier.padding(bottom = 50.dp), fontWeight = FontWeight.SemiBold)
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = TextFieldTitle.value,
            onValueChange = { TextFieldTitle.value = it },
            placeholder = { Text(text = "Title")}
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 30.dp, vertical = 8.dp)
                .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(8.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = TextFieldDescription.value,
            onValueChange = { TextFieldDescription.value = it },
            placeholder = { Text(text = "Description Task")}
        )
        Button(modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 8.dp)
            .fillMaxWidth(),colors = ButtonDefaults.buttonColors(lightGray), shape = RoundedCornerShape(8.dp), onClick = {
            val newObject = ObjectClass(
                title = TextFieldTitle.value,
                description = TextFieldDescription.value
            )
            if (TextFieldTitle.value == "" || TextFieldTitle.value == ""){
                Toast.makeText(context, "No hay datos por ingresar", Toast.LENGTH_SHORT).show()
            }else{
                myList.add(newObject)
                Toast.makeText(context, "Datos ingresados con exito!", Toast.LENGTH_SHORT).show()
            }
            TextFieldTitle.value = ""
            TextFieldDescription.value = ""
            viewModel.setData(myList)
            try {
                focusRequester.requestFocus()
            }catch (e: Exception) {
                // Maneja cualquier excepción que ocurra
                Log.e("InsertDataComponent", "Error al solicitar el foco", e)
            }
        }) {
            Text(text = "Add", color = Color.White)
        }
    }
}