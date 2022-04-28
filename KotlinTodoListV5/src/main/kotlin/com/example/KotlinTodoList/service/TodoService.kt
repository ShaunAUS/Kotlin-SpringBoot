package com.example.KotlinTodoList.service

import com.example.KotlinTodoList.database.Todo
import com.example.KotlinTodoList.database.converToEntity
import com.example.KotlinTodoList.model.http.TodoDTO
import com.example.KotlinTodoList.model.http.converToDTO
import com.example.KotlinTodoList.repository.repository
import com.example.KotlinTodoList.repository.repositoryImpl
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Service
class TodoService (

  val repositoryImpl: repositoryImpl

        ){



fun create(todoDTO: TodoDTO):TodoDTO?{

     return todoDTO.let {
        Todo().converToEntity(it)    //DTO -> ENTITY
    }.let {
        repositoryImpl.save(it)       // save 반환타입은 널일수도 ,아닐수도 있다
    }?.let {
        TodoDTO().converToDTO(it)    //ENTITY -> DTO 반환
     }

}



    fun readOne(index:Int):Todo? {
        return repositoryImpl.findOne(index)
    }



    fun readAll():MutableList<TodoDTO>{
        return repositoryImpl.findAll().map {    // map == 안에 데이터 타입 바꾸기
            TodoDTO().converToDTO(it)     // entity -> dto    // it = Todo
        }.toMutableList()     // List<dto>
    }



    fun update(todoDTO: TodoDTO):TodoDTO?{

        return todoDTO.let {
            Todo().converToEntity(it)    //DTO -> ENTITY
        }.let {
            repositoryImpl.save(it)       // save 반환타입은 널일수도 ,아닐수도 있다
        }?.let {
            TodoDTO().converToDTO(it)    //ENTITY -> DTO 반환
        }

    }



    fun delete(index:Int): Boolean {
        return repositoryImpl.delete(index)
    }


}