package hello.advanced.trace.callback

import hello.advanced.trace.logtrace.LogTrace

class TraceTemplate(
    private val trace: LogTrace
) {
    fun <T> execute(message: String, callback: TraceCallback<T>): T {
        val status = trace.begin(message)

        try {
            val result = callback.call()

            trace.end(status)

            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}