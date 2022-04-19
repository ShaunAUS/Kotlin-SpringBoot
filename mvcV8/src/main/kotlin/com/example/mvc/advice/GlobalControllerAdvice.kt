package com.example.mvc.advice

import com.example.mvc.controller.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

//@ControllerAdvice       //@Controller 에서 발생하는 예외들은 이걸 통과한다
//@RestControllerAdvice   //RestController 에서 발생하는 예외들이 이걸 통과한다
// 여기서 try-catch를 해주는 격임.
@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class])   // 특정한 것만 선택 가능
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e:RuntimeException): String {

        return "server error"
    }


    //이게 클래스안에 있으면 exception 을 global 로 타지않고 해당 컨트룰러에서 오류 처리
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexException(e:IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IndexOutofException")
    }

}