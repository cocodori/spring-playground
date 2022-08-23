package hello.springmvc.exception.servlet

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletResponse

@Controller
class ServletExController {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/error-ex")
    fun errorEx() {
        throw RuntimeException()
    }

    @GetMapping("/error-404")
    fun error404(response: HttpServletResponse) {
        response.sendError(404, "404 Error!")
    }

    @GetMapping("/error-500")
    fun error500(
        response: HttpServletResponse
    ) {
        response.sendError(500)
    }
}