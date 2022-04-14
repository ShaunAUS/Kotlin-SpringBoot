package com.example.mvc.controller.put

import com.example.mvc.model.Human
import com.example.mvc.model.Response
import com.example.mvc.model.Result
import org.apache.tomcat.jni.SSLConf.apply
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


//Response
@RestController
@RequestMapping("/api")
class PutApiConttoller {




    @PutMapping("/put-mapping")
    fun put(@RequestBody human: Human) : Response{

        //0, Response

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




    }
}