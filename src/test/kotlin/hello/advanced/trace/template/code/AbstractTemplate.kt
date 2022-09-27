package hello.advanced.trace.template.code

import org.slf4j.LoggerFactory

abstract class AbstractTemplate {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute() {
        val startTime = System.currentTimeMillis()

        call()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }

    protected abstract fun call()
}