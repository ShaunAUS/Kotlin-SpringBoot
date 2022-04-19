package com.example.mvc.controller.post

import com.example.mvc.model.Human
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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

    //exception 을 global 로 타지않고 해당 컨트룰러에서내에서 처리
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexException(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IndexOutofException")
    }
}