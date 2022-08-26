package hello.springmvc.exception.exhandler

import hello.springmvc.exception.api.MemberDto
import hello.springmvc.exception.exception.UserException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiExceptionV2Controller {
    private val log = LoggerFactory.getLogger(javaClass)

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException::class)
//    fun illegalExHandle(
//        e: IllegalArgumentException
//    ): ErrorResult {
//        log.error("[exceptionHandle] ex", e)
//        return ErrorResult("BAD", e.message)
//    }
//
//    @ExceptionHandler
//    fun userExHandle(e: UserException): ResponseEntity<ErrorResult> {
//        log.error("[exceptionHandle] ex", e)
//        val errorResult = ErrorResult("USER-EX", e.message)
//        return ResponseEntity(errorResult, HttpStatus.BAD_REQUEST)
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    fun exHandler(e: Exception): ErrorResult {
//        log.error("[exceptionHandler] ex", e)
//        return ErrorResult("EX", "내부 오류")
//    }

    @GetMapping("/api2/members/{id}")
    fun getMember(@PathVariable id: String): MemberDto {
        if (id == "ex")
            throw RuntimeException("잘못된 사용자")
        if (id == "bad")
            throw IllegalArgumentException("잘못된 입력 값")
        if (id == "user-ex")
            throw UserException("사용자 오류")

        return MemberDto(id, "hello $id")
    }
}