package hello.proxy.config.v2_dynamicproxy.handler

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.Exception
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
    private val target: Any,
    private val logTrace: LogTrace,
    private val patterns: Array<String>
): InvocationHandler {

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
        // 메서드 이름 필터
        if (!PatternMatchUtils.simpleMatch(patterns, method.name)) {
            return method.invoke(target, *args ?: arrayOf())
        }

        val message = "${method.declaringClass.simpleName}.${method.name}()"
        val status: TraceStatus = logTrace.begin(message)

        try {
            val result = method.invoke(target, *args ?: arrayOf())

            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}