package hello.springmvc.exception.servlet

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class ErrorPageController {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val ERROR_EXCEPTION = "javax.servlet.error.exception"
        const val ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type"
        const val ERROR_MESSAGE = "javax.servlet.error.message"
        const val ERROR_REQUEST_URI = "javax.servlet.error.request_uri"
        const val ERROR_SERVLET_NAME = "javax.servlet.error.servlet.name"
        const val ERROR_STATUS_CODE = "javax.servlet.error.status_code"
    }


    @RequestMapping(value = ["/error-page/500"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun errorPage500Api(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<MutableMap<String, Any?>> {
        log.info("API errorPage 500")

        val result = mutableMapOf<String, Any?>()
        val ex = request.getAttribute(ERROR_EXCEPTION) as Exception
        result["status"] = request.getAttribute(ERROR_EXCEPTION)
        result["message"] = ex.message

        val statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) as Int

        return ResponseEntity(result, HttpStatus.valueOf(statusCode))
    }

    @RequestMapping("/error-page/404")
    fun errorPage404(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String {
        log.info("errorPage 404")

        printErrorInfo(request)

        return "erorrpage/404"
    }

    @RequestMapping("/error-page/500")
    fun errorPage500(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String {
        log.info("errorPage 500")

        printErrorInfo(request)

        return "erorrpage/500"
    }

    private fun printErrorInfo(request: HttpServletRequest) {
        log.info("ERROR_EXCEPTION ex=", request.getAttribute(ERROR_EXCEPTION))
        log.info("ERROR_EXCEPTION_TYPE: {}", request.getAttribute(ERROR_EXCEPTION_TYPE))
        //ex의 경우, NestedServletException 스프링이 한 번 감싸서 반환
        log.info("ERROR_MESSAGE: {}", request.getAttribute(ERROR_MESSAGE))
        log.info("ERROR_REQUEST_URI: {}", request.getAttribute(ERROR_REQUEST_URI))
        log.info("ERROR_SERVLET_NAME: {}", request.getAttribute(ERROR_SERVLET_NAME))
        log.info("ERROR_STATUS_CODE: {}", request.getAttribute(ERROR_STATUS_CODE))
        log.info("dispatchType={}", request.dispatcherType)
    }
}
