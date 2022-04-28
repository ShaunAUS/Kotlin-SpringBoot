package com.example.KotlinTodoList.model.http

import com.example.KotlinTodoList.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDTO (


    @field:ApiModelProperty(

        value = "DB INDEX",
        example = "1",
        required = false
    )
    var index:Int?=null,

    @field:NotBlank
    var title:String?=null,


    @field:ApiModelProperty(

        value = "일정 설명",
        example = "1시 메가커피",
        required = false
    )
    var description:String?=null,

    @field:NotBlank
    @field:ApiModelProperty(

        value = "일정 시간",
        example = "2020-01-01 13:00:00",
        required = true
    )
    var schedule:String?=null,  //yyyy-MM-dd HH:mm:ss

    var createdDate:LocalDateTime?=null,

    var updatedDate:LocalDateTime?=null


        ){


@AssertTrue(message =  "포맷형태가 맞지 않습니다")
fun formatCheck():Boolean{

    return try {

        //왼쪽 항이 오른쪽 패턴에 맞나 확인
        LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        true
    }catch(e:Exception){
        false
    }

}

}

    // 확장함수 ENTITY ->DTO
    fun TodoDTO.converToDTO(todo:Todo): TodoDTO {

        return TodoDTO().apply {

            this.title = todo.title
            this.description = todo.description
            this.index = todo.index
            this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))  //문자열로
            this.createdDate = todo.createdDate
            this.updatedDate = todo.updatedDate
        }

    }

