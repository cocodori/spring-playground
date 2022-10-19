package hello.proxy.config.v2_dynamicproxy.handler

import hello.advanced.trace.logtrace.LogTrace
import java.lang.Exception
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val logTrace: LogTrace
): InvocationHandler  {

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
        val message = "${method.declaringClass.simpleName}.${method.name}()"
        val status = logTrace.begin(message)

        try {
            val result = method.invoke(target, args)

            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

}