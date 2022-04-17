package com.example.mvc.model

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*
import kotlin.math.max
import kotlin.math.min

//DTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) //지금은 deprecate
data class Human(


    @field:NotNull
    @field:Size(min=2, max=10)
    var name:String?=null,


    @field:PositiveOrZero   // 0 < 숫자를 검증 ( 0 도 포함)
    var age:String?=null,
    var address:String?=null,


    @field:Email
    var email:String?=null,


    // phone-number 로 리퀘스트 받고 반환까지
    //@JsonProperty(value = "phone-number")
    @field:Pattern(regexp = "\\\\d{3}-\\\\d{4}-\\\\d{4}")  //정규식 검증
    var phoneNumber:String?=null,


    //검증 방법 1
    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다")
    var createdAt:String?=null // yyyy-MM-dd HH:mm:ss


){

//검증 방법 2
/*//기존의 스프링 validation으로 검증 어려울떄 검증 커스터마이징
@AssertTrue     //validation 일어날때 해당 메서드를 참고해서 실행된다
private fun isValidCreatedAt():Boolean{

    return try{

        LocalDateTime.parse(this.createdAt ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
         true

    }catch(e:Exception){

         false

    }

}*/

}