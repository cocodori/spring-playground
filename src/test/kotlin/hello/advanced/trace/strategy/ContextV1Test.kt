package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV1
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV1Test {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun strategyV4() {
        val context1 = ContextV1 { log.info("비즈니스 로직1") }
        val context2 = ContextV1 { log.info("비즈니스 로직2") }

        context1.execute()
        context2.execute()
    }

    @Test
    fun strategyV3() {
        val context1 = ContextV1(strategy = object : Strategy {
            override fun call() {
                log.info("비즈니스 로직1")
            }
        })

        context1.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object : Strategy {
            override fun call() {
                log.info("비즈니스 로직 1 실행")
            }
        }

        log.info("strategyLogic1={}", strategyLogic1.javaClass)
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = object : Strategy {
            override fun call() {
                log.info("비즈니스 로직 2 실행")
            }
        }

        log.info("strategyLogic2={}", strategyLogic2.javaClass)
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()

    }

    @Test
    fun strategyV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()

        log.info("비즈니스 로직 1 실행")

        val endTime = System.currentTimeMillis()

        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()

        log.info("비즈니스 로직 2 실행")

        val endTime = System.currentTimeMillis()

        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }
}