package com.danielhrndz.todolist.model

data class ObjectClass(
    private var title: String,
    private var description: String,

    ) {
    var PropertyOne: String
        get() = title
        set(value) {
            title = value
        }

    var PropertyTwo: String
        get() = description
        set(value) {
            description = value
        }
}