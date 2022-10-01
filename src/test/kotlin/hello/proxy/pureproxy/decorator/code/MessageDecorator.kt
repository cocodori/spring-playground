package hello.proxy.pureproxy.decorator.code

import org.slf4j.LoggerFactory


class MessageDecorator(
    private val component: Component
): Component {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun operation(): String {
        log.info("MessageDecorator 실행")

        val result = component.operation()
        val decoResult = "****$result****"

        log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult)

        return decoResult
    }

}