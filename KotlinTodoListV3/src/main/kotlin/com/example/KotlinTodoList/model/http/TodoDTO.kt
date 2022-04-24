package com.example.KotlinTodoList.model.http

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDTO (

    var index:Int?=null,

    @field:NotBlank
    var title:String?=null,

    var description:String?=null,

    @field:NotBlank     //yyyy-MM-dd HH:mm:ss
    var schedule:String?=null,

    var createdDate:LocalDateTime?=null,

    var updatedDate:LocalDateTime?=null


        ){


@AssertTrue(message =  "포맷형태가 맞지 않습니다")
fun formatCheck():Boolean{

    return try {

        LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        true
    }catch(e:Exception){
        false
    }

}

}