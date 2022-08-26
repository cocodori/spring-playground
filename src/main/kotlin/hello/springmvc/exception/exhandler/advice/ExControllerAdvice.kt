package hello.springmvc.exception.exhandler.advice

import hello.springmvc.exception.exception.UserException
import hello.springmvc.exception.exhandler.ErrorResult
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice // 대상 지정하지 않으면 모든 컨트롤러에 적용
class ExControllerAdvice {

    val log = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalExHandle(e: IllegalArgumentException): ErrorResult {
        log.error("[exceptionHandle] ex", e)
        return ErrorResult("BAD", e.message)
    }

    @ExceptionHandler
    fun userExHandle(e: UserException): ResponseEntity<ErrorResult> {
        log.error("[exceptionHandle] ex", e)
        val errorResult = ErrorResult("USER=EX", e.message)
        return ResponseEntity(errorResult, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    fun exHandler(e: Exception): ErrorResult {
        log.error("[exceptionHandle] ex", e)
        return ErrorResult("EX", "내부 오류")
    }
}