package hello.springmvc.login.web.session

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.Date
import javax.servlet.http.HttpServletRequest

@RestController
class SessionInfoController {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/session-info")
    fun sessionInfo(
        request: HttpServletRequest
    ): String {
        val session = request.getSession(false) ?: return "세션이 없습니다."

        session.attributeNames.asIterator()
            .forEachRemaining { log.info("session name={}, value={}", it, session.getAttribute(it)) }

        log.info("sessionId={}", session.id)
        log.info("maxInactiveInterval={}", session.maxInactiveInterval)
        log.info("maxCreationTime={}", session.creationTime)
        log.info("lastAccessedTime={}", Date(session.lastAccessedTime))
        log.info("isNew={}", session.isNew)

        return "세션 출력"

    }

}