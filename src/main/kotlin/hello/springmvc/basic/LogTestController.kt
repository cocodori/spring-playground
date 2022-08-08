package hello.springmvc.basic

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogTestController {
    private val log = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/log-test")
    fun logTest(): String {
        val name = "Spring"

        log.trace("trace log={}", name)
        log.debug("debug log={}", name)
        log.info("info log={}", name)
        log.warn("warn log={}", name)
        log.error("error log={}", name)

        //Java에서는 문자열 더하기 식으로 쓰지 말라는데 kotlin 스타일에도 적용되는지 잘 모르겠음.
        log.debug("String concat log=$name")

        return "ok"
    }
}