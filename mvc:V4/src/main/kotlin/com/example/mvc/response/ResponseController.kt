package com.example.mvc.response

import com.example.mvc.model.Human
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//ResponseEnitty
@RestController
@RequestMapping("/api/response")
class ResponseController {


    // 1. Get
    @GetMapping("")
    fun response(@RequestParam age : Int?): ResponseEntity<String> {   //   == @RequestParam required false

        return age?.let {

            // age not null
            if(age <20){
               return ResponseEntity.status(400).body("age 는 20이상이여야 합니다")
            }

            ResponseEntity.ok("ok")

        }?: kotlin.run {

            //age == null
            return ResponseEntity.status(400).body("age 가 null 입니다")

        }


//        // 1. age == null 이면 -> 400 ERROR
//        if(age == null) {
//            return ResponseEntity.status(400).body("age 가 null 입니다")
//        }
//
//
//        // 2 . age < 20 -> 400 ERROr
//        if(age < 20) {
//            ResponseEntity.status(400).body("age 는 20이상이여야 합니다")
//        }
//
//        println(age)



    }

    // 2. Post 200
    @PostMapping
    fun post(@RequestBody human: Human?): ResponseEntity<Any> {   //   ?  = body(dto전체)가 null 일수도 있다. = 200

        return ResponseEntity.status(200).body(human)
    }

    // 3. put 201
    @PutMapping
    fun put(@RequestBody human: Human?): ResponseEntity<Human> {

        // 1. 기존데이터가  없어서 새로 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(human)

    }

    // 4. Delete 500
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Int): ResponseEntity<Any> {

        return ResponseEntity.status(500).body(null)

    }
}