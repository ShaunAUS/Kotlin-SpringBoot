package com.example.mvc.controller.get

import com.example.mvc.model.Human
import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min


@RestController
@RequestMapping("/api")
@Validated
class GetApiController {

    @GetMapping("/hello","/bye")
    fun hello (): String {
        return "hello world"
    }


    @GetMapping("/user/{name}")
    fun hello2 (@PathVariable name : String) : String {

        println(name)
        return name

    }

    // pathVariable 이름 곂칠때  = value
    @GetMapping("/user/find/{name}")
    fun hello3 (@PathVariable(value = "name") _name : String) : String {

        var name ="kotlin"

        println(_name)
        return _name

    }

    @GetMapping("/param-test")
    fun hello4(
        @RequestParam(value = "name") name : String,

        @NotNull  //bean 이 아니기떄문에 controller에다가 validate 적어줘야함 
        @Min(value = 20, message = "age는 20보다 커야합니다")
        @RequestParam(value = "age") age : String
    ): String {
        println(name + age)

        return name+age

    }

    //코틀린 dto  request 테스트
    @GetMapping("/dto-test")
    fun hello5(human : Human): Human {

        println(human)
        return human
    }

    //코틀린 map request 테스트
    @GetMapping("/mapRequest")
    fun hello6(@RequestParam map :Map<String,Any>) : Map<String,Any>{

        println(map)
        return map

    }
}