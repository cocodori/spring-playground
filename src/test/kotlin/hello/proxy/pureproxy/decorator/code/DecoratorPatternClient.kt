package hello.proxy.pureproxy.decorator.code

import org.slf4j.LoggerFactory

class DecoratorPatternClient(
    private val component: Component
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute() {
        val result = component.operation()
        log.info("result={}", result)
    }

}