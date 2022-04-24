package com.example.KotlinTodoList.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import javax.validation.Validation


//@Valid 테스트
class TodoDTOTest {


    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDTOTest(){

        val todoDto = TodoDTO().apply {

            this.title ="test"
            this.description = ""
            this.schedule = "2022-01-01 13:00:00"

        }

        val result = validator.validate(todoDto)



        result.forEach{

            println(it.propertyPath.last().name)
            println(it.message)
            println(it.invalidValue)

        }


        Assertions.assertEquals(true,result.isEmpty())
    }
}