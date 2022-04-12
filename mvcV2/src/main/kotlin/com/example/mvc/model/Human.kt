package com.example.mvc.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

//DTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) //지금은 deprecate
data class Human(

    var name:String?=null,
    var age:String?=null,
    var address:String?=null,


    // phone-number 로 리퀘스트 받고 반환까지
    //@JsonProperty(value = "phone-number")
    var phoneNumber:String?=null


)