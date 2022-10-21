package hello.proxy.config.v3_proxyfactory.advice

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.LoggerFactory
import java.lang.Exception

class LogTraceAdvice(
    private val logTrace: LogTrace
): MethodInterceptor {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun invoke(invocation: MethodInvocation): Any? {
        val method = invocation.method

        val status: TraceStatus = logTrace.begin("${method.declaringClass.simpleName}.${method.name}()")

        try {
            val result = invocation.proceed()

            logTrace.end(status)

            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}