package com.example.KotlinTodoList.repository

import com.example.KotlinTodoList.database.Todo
import com.example.KotlinTodoList.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class repositoryImpl :repository{

    @Autowired
    lateinit var todoDataBase: TodoDataBase

    override fun save(todo: Todo): Todo? {

        // 1. Index가 있는 경우(수정)
    return todo.index?.let {index->

        //있으면 덮어씌운다
         findOne(index)?.apply {
                this.updatedDate =  LocalDateTime.now()
                this.title  = todo.title
                this.description = todo.description
                this.schedule = todo.schedule

            }

        // 2. Index가 없는경우 (insert문)
        }?:kotlin.run {

             todo.apply {

                this.index = ++todoDataBase.index
                this.createdDate = LocalDateTime.now()
                this.updatedDate = LocalDateTime.now()


            }.run {
                todoDataBase.todoLists.add(todo)
                this
            }
        }

    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {

        return try{

            todoList.forEach{       //for 문을 돌면서 하나씩 저장
                save(it)
            }
            true

        }catch (e:Exception){

        false
        }
    }

    /*override fun update(todo: Todo): Todo {
        TODO("Not yet implemented")
    }*/

    override fun delete(index: Int): Boolean {

        //todo가 값이 있으면
        return findOne(index)?.let {

            todoDataBase.todoLists.remove(it)
            true
        //todo가 null이면
        }?: kotlin.run {

            false
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoLists.filter {it.index == index}.first()
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoLists
    }
}