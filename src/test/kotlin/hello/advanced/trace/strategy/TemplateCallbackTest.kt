package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.template.Callback
import hello.advanced.trace.strategy.code.template.TimeLogTemplate
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateCallbackTest {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun callbackV2() {
        val template = TimeLogTemplate()
        template.execute { log.info("1 실행") }
        template.execute { log.info("2 실행") }
    }

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()

        template.execute(object: Callback {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        })

        template.execute(object: Callback {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        })
    }
}