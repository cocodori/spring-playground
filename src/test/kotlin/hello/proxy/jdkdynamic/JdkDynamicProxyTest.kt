package hello.proxy.jdkdynamic

import hello.proxy.jdkdynamic.code.*
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.reflect.Proxy


class JdkDynamicProxyTest {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun dynamicA() {
        val target: AInterface = AImpl()
        val handler = TimeInvocationHandler(target)
        val proxy = Proxy.newProxyInstance(
            AInterface::class.java.classLoader,
            arrayOf(AInterface::class.java),
            handler
        ) as AInterface

        proxy.call()
        log.info("targetClass={}", target.javaClass)
        log.info("proxyClass={}", proxy.javaClass)
    }

    @Test
    fun dynamicB() {
        val target: BInterface = BImpl()
        val handler = TimeInvocationHandler(target)
        val proxy = Proxy.newProxyInstance(
            BInterface::class.java.classLoader,
            arrayOf(BInterface::class.java),
            handler
        ) as BInterface

        proxy.call()
        log.info("targetClass={}", target.javaClass)
        log.info("proxyClass={}", proxy.javaClass)
    }

}