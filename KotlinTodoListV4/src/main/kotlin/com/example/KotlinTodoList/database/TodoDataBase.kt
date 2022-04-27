package com.example.KotlinTodoList.database

data class TodoDataBase (

    var index:Int=0,
    var todoLists:MutableList<Todo> = mutableListOf()

){

    fun init(){

        this.index = 0
        this.todoLists = mutableListOf() // 초기화
        println("[DEBUG] todo database init")

    }

}