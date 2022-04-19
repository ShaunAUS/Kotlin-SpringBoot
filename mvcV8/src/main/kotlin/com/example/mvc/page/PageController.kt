package com.example.mvc.page

import com.example.mvc.model.Human
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


// @controller =  page or data 반환
//data 반환시 @ResponseBody 해줘야함
// @RestController = @controller + @ResponseBody
// static에 페이지 있다   // templates -> 타임리프 설정해줘야함
@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String{

        println("init main")
        return "main.html"

    }

    @ResponseBody
    @GetMapping("/test")
    fun test(): Human {

       return Human().apply {

            this.name = "sam"
        }

        //return "main.html"

    }


    }