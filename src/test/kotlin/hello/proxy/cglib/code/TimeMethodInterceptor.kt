package hello.proxy.cglib.code

import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class TimeMethodInterceptor(
    private val target: Any
) : MethodInterceptor {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun intercept(
        any: Any?,
        method: Method?,
        args: Array<out Any>?,
        proxy: MethodProxy?
    ): Any? {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis()

        val result = proxy?.invoke(target, args)

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime={}", resultTime)
        return result
    }
}