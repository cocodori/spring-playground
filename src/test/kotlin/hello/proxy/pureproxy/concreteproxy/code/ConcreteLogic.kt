package hello.proxy.pureproxy.concreteproxy.code

import org.slf4j.LoggerFactory

open class ConcreteLogic {

    private val log = LoggerFactory.getLogger(javaClass)

    open fun operation(): String {
        log.info("ConcreteLogic 실행")
        return "data"
    }
}