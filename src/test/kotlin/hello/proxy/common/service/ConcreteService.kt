package hello.proxy.common.service

import org.slf4j.LoggerFactory

open class ConcreteService {

    private val log = LoggerFactory.getLogger(javaClass)

    open fun call() {
        log.info("ConcreteService 호출")
    }
}