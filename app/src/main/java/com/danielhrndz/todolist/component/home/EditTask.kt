package com.danielhrndz.todolist.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielhrndz.todolist.model.ObjectClass
import com.danielhrndz.todolist.ui.theme.dark
import com.danielhrndz.todolist.ui.theme.lightGray

@Composable
fun EditTask(
    index: Int,
    item: ObjectClass,
    data: MutableState<MutableList<ObjectClass>>
) {
    var editedText1 by remember { mutableStateOf(item.PropertyOne) }
    var editedText2 by remember { mutableStateOf(item.PropertyTwo) }
    val editingIndex = remember { mutableIntStateOf(-1) }

    Box(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(lightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 5.dp,
                    end = 5.dp,
                    top = 2.dp,
                    bottom = 2.dp
                ),
        ) {
            if (editingIndex.value == index) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10))
                        .background(lightGray)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            label = { Text("Title") },
                            value = editedText1,
                            onValueChange = { editedText1 = it },
                            singleLine = true,
                            modifier = Modifier
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 15.dp,
                                    bottom = 5.dp
                                )
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    item.PropertyOne = editedText1
                                    item.PropertyTwo = editedText2
                                    editingIndex.value = -1
                                }
                            )
                        )
                        OutlinedTextField(
                            label = { Text("Description Task") },
                            value = editedText2,
                            onValueChange = { editedText2 = it },
                            singleLine = true,
                            modifier = Modifier
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 15.dp,
                                    bottom = 5.dp
                                )
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    item.PropertyOne = editedText1
                                    item.PropertyTwo = editedText2
                                    editingIndex.value = -1
                                }
                            )
                        )
                        Button(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(dark),
                            shape = RoundedCornerShape(8.dp),
                            onClick = {
                                val currentItem = data.value[index]
                                currentItem.PropertyOne = editedText1
                                currentItem.PropertyTwo = editedText2

                                editingIndex.value = -1

                                editedText1 = ""
                                editedText2 = ""
                            }
                        ) {
                            Text(text = "Modify Task", color = Color.White)
                        }
                    }
                }
            } else {
                Text(
                    text = item.PropertyOne,
                    modifier = Modifier
                        .clickable {
                            if (editingIndex.value != -1) {
                                editingIndex.value = -1
                            }
                            editedText1 = item.PropertyOne
                            editedText2 = item.PropertyTwo
                            editingIndex.value = index
                        }
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 15.dp,
                            bottom = 5.dp
                        )
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = item.PropertyTwo,
                    modifier = Modifier
                        .clickable {
                            if (editingIndex.value != -1) {
                                editingIndex.value = -1
                            }
                            editedText1 = item.PropertyOne
                            editedText2 = item.PropertyTwo
                            editingIndex.value = index
                        }
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 15.dp
                        )
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}
