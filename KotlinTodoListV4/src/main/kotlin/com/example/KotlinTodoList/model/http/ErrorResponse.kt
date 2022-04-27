package com.example.KotlinTodoList.model.http

import java.time.LocalDateTime

data class ErrorResponse (


    var message:String?=null,

    var httpStatus:String?=null,

    var httpMethod:String?=null,

    var resultCode:String?=null,

    var path:String?=null,

    var timestamp:LocalDateTime?=null,

    var eroors:MutableList<Error>?=null



        )


class Error(

    var field:String?=null,
    var message:String?=null,

    var value:Any?=null,

)