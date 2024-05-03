package com.danielhrndz.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielhrndz.todolist.data.objectList
import com.danielhrndz.todolist.model.ObjectClass
import kotlinx.coroutines.launch

class DataViewModel:ViewModel(){
    fun setData(myList: MutableList<ObjectClass>){
        viewModelScope.launch {
            objectList.value += myList
        }
    }
    fun getData(): MutableList<ObjectClass>{
        return objectList.value
    }
}