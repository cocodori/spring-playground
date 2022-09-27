package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

class ContextV1(
    private val strategy: Strategy
) {
    
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute() {
        val startTime = System.currentTimeMillis()

        strategy.call()

        val endTime = System.currentTimeMillis()

        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }
}