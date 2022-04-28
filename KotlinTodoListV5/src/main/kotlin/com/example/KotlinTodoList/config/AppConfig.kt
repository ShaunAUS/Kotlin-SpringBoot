package com.example.KotlinTodoList.config

import com.example.KotlinTodoList.database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AppConfig {


    @Bean(initMethod = "init")   // bean이 만들어질떄 이 method를 실행하라
    fun dataBase(): TodoDataBase {
        return TodoDataBase()
    }
}