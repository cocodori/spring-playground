package hello.springmvc.basic.requestmapping

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class MappingController {

    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * HTTP 메서드 모두 허용
     */
    @RequestMapping("/hello-basic")
    fun helloBasic(): String {
        log.info("helloBasic")
        return "ok"
    }

    @RequestMapping(value = ["/mapping-get-v1"], method = [RequestMethod.GET])
    fun mappingGetV1(): String {
        log.info("mappingGetV1")
        return "ok"
    }

    @GetMapping("/mapping-get-v2")
    fun mappingGetV2(): String {
        log.info("mapping-get-v2")
        return "ok"
    }

    @GetMapping("/mapping/{userId}")
    fun mappingPath(@PathVariable("userId") data: String): String {
        log.info("mappingPath userId={}", data)
        return "ok"
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    fun mappingPath(
        @PathVariable userId: String,
        @PathVariable orderId: String
    ): String {
        log.info("mappingPath userId={}, orderId={}", userId, orderId)

        return "ok"
    }

    @GetMapping(value = ["/mapping-param"], params = ["mode=debug"])
    fun mappingParam(): String {
        log.info("mappingParam")
        return "ok"
    }

    @PostMapping(value = ["/mapping-consume"], consumes = ["application/json"])
    fun mappingConsumes(): String {
        log.info("mappingConsumes")

        return "ok"
    }

    @PostMapping(value = ["/mapping-produce"], produces = ["text/html"])
    fun mappingProduces(): String {
        log.info("mappingProduces")

        return "ok"
    }

}