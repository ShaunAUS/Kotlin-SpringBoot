package com.example.KotlinTodoList.repository

import com.example.KotlinTodoList.database.Todo

interface repository {

    fun save(todo: Todo):Todo?
    fun saveAll(todoList:MutableList<Todo>):Boolean

   // fun update(todo:Todo):Todo     // JPA를 사용할경우 save만 제공   없으면 insert, 있으면 update
    fun delete(index:Int):Boolean

    fun findOne(index:Int):Todo?                     //조회
    fun findAll():MutableList<Todo>                 //다중 조회


}