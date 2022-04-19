package com.example.mvc.controller.exception

import com.example.mvc.model.Error
import com.example.mvc.model.ErrorResponse
import com.example.mvc.model.Human
import com.example.mvc.model.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.Size


@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("")
    fun ex(){
        throw RuntimeException("강제예외발생")

    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun postException(e:MethodArgumentNotValidException,request: HttpServletRequest): ResponseEntity<ErrorResponse> {

        val erros = mutableListOf<Error>()


        e.bindingResult.allErrors.forEach{errorObject ->

            val error = Error().apply {

                val field = errorObject as FieldError

                this.field = field.field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue    // 오류를 일으킨 값


        }

            erros.add(error)

        }

        // 2. ErrorResponse

        val errorResponse = ErrorResponse().apply {

            this.resultCode = "Fail"
            this.message = "요청에 에러가 발생하였습니다."
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.path = request.requestURI   // 요청 URI
            this.timeStamp = LocalDateTime.now()
            this.httpMethod = request.method   // 요청 Method

            this.erros = erros

        }
        //3. ResponseEntity

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


    @PostMapping("")
    fun postExceptionHanlder(
        @Valid human: Human
    ){

    }

//====================================================================================================================

    //밑에 get메서드 파라미터 오류시 오류처리
    // request 는 요청정보 다들어가 있다.
    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintException(a:ConstraintViolationException,request:HttpServletRequest):ResponseEntity<ErrorResponse>{

        //  1.에러분석
        val errors = mutableListOf<Error>()


        //오류내용들
        a.constraintViolations.forEach{

            //필드이름 + 메세지
            val field = it.propertyPath.last().name    // propertyPath 는 배열이며 마지막에오는게 '변수이름' 이다
            val message = it.message

            val error =Error().apply {

                this.field = field
                this.message = message
                this.value = it.invalidValue  // invalidValue  = 유효하지않은값(오류발생시킨 값)
            }

            errors.add(error)
        }


        // 2. ErrorResponse

        val errorResponse = ErrorResponse().apply {

            this.resultCode = "Fail"
            this.message = "요청에 에러가 발생하였습니다."
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.path = request.requestURI   // 요청 URI
            this.timeStamp = LocalDateTime.now()
            this.httpMethod = request.method   // 요청 Method

            this.erros = erros

        }
        //3. ResponseEntity

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    fun get(

        @Size(min = 2, max = 10)
        @RequestParam(value = "name") name :String,

        @Min(10)
        @RequestParam(value = "age") age :Int,

    ){
        println(name+age)

    }



}