package com.example.KotlinTodoList.database

import com.example.KotlinTodoList.model.http.TodoDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//컬럼
data class Todo(

    var index:Int?=null,
    var title:String?=null,
    var description:String?=null,
    var schedule:LocalDateTime?=null,
    var createdDate:LocalDateTime?=null,
    var updatedDate:LocalDateTime?=null


)
// 확장함수 DTO ->ENTITY
fun Todo.converToEntity(todoDto:TodoDTO): Todo{

    return Todo().apply {

        this.title = todoDto.title
        this.description = todoDto.description
        this.index = todoDto.index
        this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createdDate = todoDto.createdDate
        this.updatedDate = todoDto.updatedDate
    }

}