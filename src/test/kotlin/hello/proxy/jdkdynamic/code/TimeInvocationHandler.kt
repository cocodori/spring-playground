package hello.proxy.jdkdynamic.code

import org.slf4j.LoggerFactory
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
    private val target: Any
): InvocationHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any {
        log.info("TimeProxy 실행")

        val startTime = System.currentTimeMillis()
        val result = method.invoke(target)
        val endTime = System.currentTimeMillis()

        log.info("TimeProxy 종료 resultTime={}", endTime - startTime)

        return result
    }
}