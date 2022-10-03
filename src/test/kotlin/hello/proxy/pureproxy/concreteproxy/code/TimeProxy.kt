package hello.proxy.pureproxy.concreteproxy.code

import org.slf4j.LoggerFactory

class TimeProxy(
    private val realLogic: ConcreteLogic
): ConcreteLogic() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun operation(): String {
        log.info("TimeDecorator 실행")

        val startTime = System.currentTimeMillis()

        val result = realLogic.operation()

        val endTime = System.currentTimeMillis()

        val resultTime = endTime - startTime

        log.info("TimeDecorator 종료 resultTime={}", resultTime)

        return result
    }
}