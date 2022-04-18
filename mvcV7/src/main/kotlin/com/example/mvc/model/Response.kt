package com.example.mvc.model

import com.fasterxml.jackson.annotation.JsonProperty

//복잡한 Response 만들기
data class Response(

    var result:Result?=null,
    var description:String?=null,

    @JsonProperty(value = "user")
    var humanRequest:MutableList<Human>?=null

)


data class Result(

    var resultCode:String?=null,
    var resultMessage:String?=null
)