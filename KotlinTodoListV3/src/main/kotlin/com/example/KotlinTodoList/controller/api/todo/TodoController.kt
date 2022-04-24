package com.example.KotlinTodoList.controller.api.todo

import com.example.KotlinTodoList.model.http.TodoDTO
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/todo")
class TodoController {

    @GetMapping("")
    fun read(@RequestParam(required = false) index:Int?){


    }


    @PostMapping("")
    fun create(@Valid @RequestBody todoDTO: TodoDTO){

    }

    @PostMapping("")
    fun update(@Valid @RequestBody todoDTO: TodoDTO){

    }

    @DeleteMapping("")
    fun delete(@PathVariable index: Int){

    }

}