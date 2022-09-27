package hello.advanced.trace.template

import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLogic1
import hello.advanced.trace.template.code.SubClassLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateMethodTest {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun templateMethodV2() {
        val template1 = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직 1 실행")
            }
        }

        val template2 = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직 2 실행")
            }
        }

        template1.execute()
        template2.execute()
    }

    @Test
    fun templateMethodV1() {
        val template1 = SubClassLogic1()
        template1.execute()

        val template2 = SubClassLogic2()
        template2.execute()
    }

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()

        log.info("비즈니스 로직1 실행")

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()

        log.info("비즈니스 로직2 실행")

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        log.info("resultTime={}", resultTime)
    }
}
