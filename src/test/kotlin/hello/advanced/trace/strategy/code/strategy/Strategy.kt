package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

fun interface Strategy {
    fun call()
}

class StrategyLogic1: Strategy {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun call() {
        log.info("비즈니스 로직1")
    }
}

class StrategyLogic2: Strategy {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun call() {
        log.info("비즈니스 로직2")
    }
}