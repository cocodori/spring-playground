package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV2
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV2Test {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun strategyV3() {
        val context = ContextV2()
        context.execute { log.info("비즈니스 로직 1 실행") }
        context.execute { log.info("비즈니스 로직 2 실행") }
    }

    @Test
    fun strategyV2() {
        val context = ContextV2()
        context.execute(object: Strategy {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        })

        context.execute(object: Strategy {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        })
    }

    @Test
    fun strategyV1() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }
}