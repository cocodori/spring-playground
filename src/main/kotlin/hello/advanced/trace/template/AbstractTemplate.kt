package hello.advanced.trace.template

import hello.advanced.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(
    private val trace: LogTrace
) {
    protected abstract fun call(): T

    fun execute(message: String): T {
        val status = trace.begin(message)

        try {
            val result: T = call()

            trace.end(status)

            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}