package hello.springmvc.exception.resolver

import com.fasterxml.jackson.databind.ObjectMapper
import hello.springmvc.exception.exception.UserException
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import java.io.IOException
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserHandlerExceptionResolver: HandlerExceptionResolver {

    private val log = LoggerFactory.getLogger(javaClass)
    private val objectMapper = ObjectMapper()

    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception
    ): ModelAndView? {
        try {
            if (ex is UserException) {
                log.info("UserException resolver to 400")
                val acceptHeader = request.getHeader("accept")
                response.status = HttpServletResponse.SC_BAD_REQUEST

                if ("application/json" == acceptHeader) {
                    val errorResult = mutableMapOf<String, Any?>()
                    errorResult["ex"] = ex.javaClass
                    errorResult["message"] = ex.message

                    val result = objectMapper.writeValueAsString(errorResult)

                    response.contentType = "application/json"
                    response.characterEncoding = "utf-8"
                    response.writer.write(result)

                    return ModelAndView()
                } else {
                    return ModelAndView("error/500")
                }
            }
        } catch (e: IOException) {
            log.error("resolver ex", e)
        }

        return null
    }


}