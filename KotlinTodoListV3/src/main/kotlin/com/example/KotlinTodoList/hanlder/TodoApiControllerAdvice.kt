package com.example.KotlinTodoList.hanlder

import com.example.KotlinTodoList.controller.api.todo.TodoController
import com.example.KotlinTodoList.model.http.Error
import com.example.KotlinTodoList.model.http.ErrorResponse
import org.apache.tomcat.util.descriptor.web.ErrorPage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest


@ControllerAdvice(basePackageClasses = [TodoController::class])  //TOdoController에만 동작하도록
class TodoApiControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e:MethodArgumentNotValidException,request:HttpServletRequest): ResponseEntity<ErrorResponse> {

        val list = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach{errorObject ->

            val err = com.example.KotlinTodoList.model.http.Error().apply{

                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue

            }

            list.add(err)
        }


        //클라에게 전달할 에러메세지
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpMethod = request.method
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.eroors = list

        }

        return ResponseEntity.badRequest().body(errorResponse)

    }

}