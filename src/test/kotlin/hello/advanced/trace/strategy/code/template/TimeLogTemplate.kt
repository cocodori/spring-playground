package hello.advanced.trace.strategy.code.template

import org.slf4j.LoggerFactory

class TimeLogTemplate {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()

        callback.call()

        val endTime = System.currentTimeMillis()

        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }
}