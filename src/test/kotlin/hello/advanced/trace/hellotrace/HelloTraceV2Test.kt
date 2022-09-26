package hello.advanced.trace.hellotrace

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HelloTraceV2Test {

    @Test
    fun begin_end_level3() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello1")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        val status3 = trace.beginSync(status2.traceId, "hello3")

        trace.end(status3)
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun begin_exception_level3() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello1")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        val status3 = trace.beginSync(status2.traceId, "hello3")

        trace.exception(status3, IllegalStateException())
        trace.exception(status2, IllegalStateException())
        trace.exception(status1, IllegalStateException())
    }
}