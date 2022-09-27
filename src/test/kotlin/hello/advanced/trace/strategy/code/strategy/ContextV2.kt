package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

class ContextV2 {
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()

        strategy.call()

        val endTime = System.currentTimeMillis()

        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }
}