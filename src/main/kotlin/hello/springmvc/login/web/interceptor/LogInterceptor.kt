package hello.springmvc.login.web.interceptor

import org.slf4j.LoggerFactory
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import java.util.UUID
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogInterceptor : HandlerInterceptor {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val LOG_ID = "logId"
    }

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        val requestURI = request.requestURI
        val uuid = UUID.randomUUID().toString()
        request.setAttribute(LOG_ID, uuid)

        log.info("Request [{}][{}][{}][{}]", uuid, request.dispatcherType, requestURI, handler)
        return true //false면 진행을 멈추고 컨트롤러까지 가지 않는다.
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {
        log.info("postHandle [{}]", modelAndView)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val requestURI = request.requestURI
        val logId = request.getAttribute(LOG_ID)
        log.info("Response [{}][{}][{}]", logId, request.dispatcherType, requestURI)

        ex?.let {
            log.error("afterCompletion error!!", ex)
        }
    }
}