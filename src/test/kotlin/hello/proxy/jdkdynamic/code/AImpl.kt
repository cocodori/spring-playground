package hello.proxy.jdkdynamic.code

import org.slf4j.LoggerFactory

class AImpl: AInterface {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun call(): String {
        log.info("A 호출")
        return "A"
    }


}