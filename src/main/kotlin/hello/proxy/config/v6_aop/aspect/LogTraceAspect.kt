package hello.proxy.config.v6_aop.aspect

import hello.advanced.trace.logtrace.LogTrace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import java.lang.Exception

//@Aspect
class LogTraceAspect(
    private val logTrace: LogTrace
) {

    @Around("execution(* hello.proxy.app..*(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        val status = logTrace.begin(joinPoint.signature.toShortString())
        try {
            val result = joinPoint.proceed()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

}