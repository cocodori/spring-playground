package hello.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import hello.springmvc.basic.HelloData
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class SpringBodyJsonController {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        log.info("messageBody={}", messageBody)
        val data: HelloData = objectMapper.readValue(messageBody, HelloData::class.java)
        log.info("username={}, age={}", data.username, data.age);

        response.writer.write("ok")
    }

    @ResponseBody
    @PostMapping
    fun requestBodyJsonV2(
        @RequestBody messageBody: String,
    ): String {
        val data: HelloData = objectMapper.readValue(messageBody, HelloData::class.java)

        log.info("username={}, age={}", data.username, data.age)

        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(
        @RequestBody data: HelloData
    ): String {
        log.info("username={}, age={}", data.username, data.age)

        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    fun requestBodyJsonV4(httpEntity: HttpEntity<HelloData>): String {
        val data: HelloData? = httpEntity.body
        log.info("username={}, age={}", data?.username, data?.age)

        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    fun requestBodyJsonV5(
        @RequestBody data: HelloData
    ): HelloData {
        log.info("username={}, age={}", data.username, data.age)

        return data
    }

}