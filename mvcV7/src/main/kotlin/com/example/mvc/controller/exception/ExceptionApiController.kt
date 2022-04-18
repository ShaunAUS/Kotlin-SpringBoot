package com.example.mvc.controller.exception

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("")
    fun ex(){
        throw RuntimeException("강제예외발생")

    }



}