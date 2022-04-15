package com.example.mvc.page

import com.example.mvc.model.Human
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


// 특정한 html 페이지를 내릴떄
// static에 페이지 있다   // templates -> 타임리프 설정해줘야함
@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String{

        println("init main")
        return "main.html"

    }

    @ResponseBody  // @Controller 페이지에서 Json 형태로 내려야 할떄
    @GetMapping("/test")
    fun test(): Human {

       return Human().apply {

            this.name = "sam"
        }



        //return "main.html"

    }


    }