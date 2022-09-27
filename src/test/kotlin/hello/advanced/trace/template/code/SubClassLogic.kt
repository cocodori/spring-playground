package hello.advanced.trace.template.code

import org.slf4j.LoggerFactory

class SubClassLogic1: AbstractTemplate() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun call() {
        log.info("비즈니스 로직1 실행")
    }
}

class SubClassLogic2: AbstractTemplate() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun call() {
        log.info("비즈니스 로직2 실행")
    }

}