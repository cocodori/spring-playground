package hello.springmvc.basic.request

import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Locale
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class RequestHeaderController {

    private val log = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/headers")
    fun header(
        request: HttpServletRequest,
        response: HttpServletResponse,
        httpMethod: HttpMethod,
        locale: Locale,
        @RequestHeader headerMap: MultiValueMap<String, String>,
        @RequestHeader("host") host: String,
        @CookieValue(value = "myCookie", required = false) cookie: String?,
    ): String {
        log.info("request={}", request)
        log.info("response={}", response)
        log.info("httpMethod={}", httpMethod)
        log.info("locale={}", httpMethod)
        log.info("headerMap={}", headerMap)
        log.info("header host={}", host)
        log.info("myCookie={}", cookie)

        return "ok"
    }
}