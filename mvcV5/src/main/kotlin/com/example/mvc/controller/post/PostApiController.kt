package com.example.mvc.controller.post

import com.example.mvc.model.Human
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class PostApiController {


    //Object-Mapper(자동)
    // object-> Json
    //Json -> object
    @PostMapping("/post-mapping")
    fun pos(@RequestBody human: Human) : Human{   //Jason ->Object

        println(human)
        return human //Object ->Json

    }
}