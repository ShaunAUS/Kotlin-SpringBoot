package com.example.KotlinTodoList.database

import java.time.LocalDateTime

//컬럼
data class Todo(

    var index:Int?=null,
    var title:String?=null,
    var description:String?=null,
    var schedule:LocalDateTime?=null,
    var createdDate:LocalDateTime?=null,
    var updatedDate:LocalDateTime?=null




)