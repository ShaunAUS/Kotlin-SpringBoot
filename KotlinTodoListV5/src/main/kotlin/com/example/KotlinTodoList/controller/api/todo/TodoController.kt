package com.example.KotlinTodoList.controller.api.todo

import com.example.KotlinTodoList.database.Todo
import com.example.KotlinTodoList.model.http.TodoDTO
import com.example.KotlinTodoList.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.coyote.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(description = "일정관리 todo")
@RestController
@RequestMapping("/api/todo")
class TodoController (

    val todoService:TodoService

        ){

    //단건조회
    @ApiOperation(value = "일정확인", notes = "일정확인API")    // API에 대한 설명
    @GetMapping("test-find")
    fun read(
        @ApiParam(value = "투두리스트 index 값")           //매개변수에 대한 설명
        @RequestParam(required = false) index:Int?): ResponseEntity<Any?>? {

        //index 있는경우
        return index?.let {
            todoService.readOne(it)
        }?.let {
            return ResponseEntity.ok(it)
        }

        //index가 없는 경우  -> 전체조회 url로 redirect
        ?: kotlin.run {
                return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION,"/api/todo/test-find-all").build()
            }
    }

    //전체조회
    @GetMapping("/test-find-all")
    fun readAll(): MutableList<TodoDTO> {
        return todoService.readAll()

    }


    @PostMapping("test-create")
    fun create(@Valid @RequestBody todoDTO: TodoDTO): ResponseEntity<TodoDTO> {

        return ResponseEntity.status(201).body(todoService.create(todoDTO))

    }

    @PostMapping("test-change")   // create 201   update 200
    fun update(@Valid @RequestBody todoDTO: TodoDTO): TodoDTO? {

        return todoService.create(todoDTO)
    }

    @DeleteMapping("test-delete")
    fun delete(@PathVariable index: Int): ResponseEntity<Any>{

        if(!todoService.delete(index)){
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()
    }

}