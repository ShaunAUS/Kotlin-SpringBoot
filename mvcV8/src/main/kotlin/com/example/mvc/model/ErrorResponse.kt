package com.example.mvc.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse (

    @field:JsonProperty("result_code")
    var resultCode:String?=null,

    @field:JsonProperty("http_status")
    var httpStatus:String?=null,
    var message:String?=null,

    @field:JsonProperty("http_method ")
    var httpMethod:String?=null,
    var path:String?=null,
    var timeStamp:LocalDateTime?=null,
    var erros:MutableList<Error>?= mutableListOf()

        )


data class Error(

    var field:String?=null,
    var message:String?=null,
    var value:Any?=null
)