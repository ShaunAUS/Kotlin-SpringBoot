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


    //@Valid 는 entity field에 적어놓은 조건 검사 실시
    @PutMapping("/put-mapping")
    fun put(@Valid @RequestBody human: Human, bindingResult : BindingResult) : ResponseEntity<String> {


        //500 error
        //Valid 결과가 bindingResult에 담긴다(엔티티 조건 오류시 )
        if(bindingResult.hasErrors()){


            val result = StringBuilder()
            //메세지와 + 필드명
            bindingResult.allErrors.forEach{

                val err = it as FieldError   //필드는 형변환 필요하다
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