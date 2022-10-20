package hello.proxy.common.advice

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.LoggerFactory

class TimeAdvice: MethodInterceptor {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun invoke(invocation: MethodInvocation): Any? {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis();

        invocation.proceed()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime={}", resultTime)

        return resultTime
    }

}