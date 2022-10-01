package hello.proxy.pureproxy.proxy.code

import org.slf4j.LoggerFactory

class RealSubject: Subject {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun operation(): String {
        log.info("실제 객체 호출")
        Thread.sleep(1000)
        return "data"
    }
}