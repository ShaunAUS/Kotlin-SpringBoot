package com.example.mvc.controller.put

import com.example.mvc.model.Human
import com.example.mvc.model.Response
import com.example.mvc.model.Result
import org.apache.logging.log4j.message.Message
import org.apache.tomcat.jni.SSLConf.apply
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


//Response
@RestController
@RequestMapping("/api")
class PutApiConttoller {


    @PutMapping("/put-mapping")
    fun put(@Valid @RequestBody human: Human, bindingResult : BindingResult) : ResponseEntity<String> {


        //Valid 결과가 bindingResult에 담긴다 메세지와 오류내용이 들어있다.
        if(bindingResult.hasErrors()){


            val result = StringBuilder()
            //500 error
            bindingResult.allErrors.forEach{

                val err = it as FieldError
                val msg = it.defaultMessage

                result.append(err.field +  ":" +msg)

            }

            return ResponseEntity.badRequest().body(result.toString())

        }

        return ResponseEntity.ok("")

       /* //0, Response
        return Response().apply {

            //1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }

            //2. description
            }.apply {

                this.description = "~~~~~"


            //3. user MutableList
            }.apply {

                val userList = mutableListOf<Human>()

                userList.add(human)

                userList.add(Human().apply {

                    this.name = "mm"
                    this.age = "22"
                    this.address = "seoul"
                    this.phoneNumber = "0101123333"
                })


            this.humanRequest = userList

        }




    }*/
}
}