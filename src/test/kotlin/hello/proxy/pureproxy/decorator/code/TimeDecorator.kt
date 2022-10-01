package hello.proxy.pureproxy.decorator.code

import org.slf4j.LoggerFactory

class TimeDecorator(
    private val component: Component
): Component {

    val log = LoggerFactory.getLogger(javaClass)

    override fun operation(): String {
        log.info("TimeDecorator 실행")
        val startTime = System.currentTimeMillis()

        val result = component.operation()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime)

        return result
    }
}