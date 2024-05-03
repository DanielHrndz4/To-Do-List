package com.danielhrndz.todolist.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.danielhrndz.todolist.model.ObjectClass

val objectList: MutableState<MutableList<ObjectClass>> = mutableStateOf(mutableListOf())