package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HelloTraceV2 {
    private val log = LoggerFactory.getLogger(javaClass);

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X--"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()

        log.info("[${traceId.id} ${addSpace(START_PREFIX, traceId.level)} $message")

        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(beforeTraceId: TraceId, message: String): TraceStatus {
        val nextId = beforeTraceId.createNextId()
        val startTimeMs = System.currentTimeMillis()

        log.info("[${nextId.id}] ${addSpace(START_PREFIX, nextId.level)} $message")

        return TraceStatus(nextId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status)
    }

    fun exception(status: TraceStatus,e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception? = null) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        if (e == null) {
            log.info("[${traceId.id}] ${addSpace(COMPLETE_PREFIX, traceId.level)} ${status.message} time=${resultTimeMs}ms")
        } else {
            log.error("[${traceId.id}] ${addSpace(EX_PREFIX, traceId.level)} ${status.message} time=${resultTimeMs}ms, ex=$e")
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()

        for (i in 0 until level) {
            val space = if (i == level - 1) "|${prefix}" else "|   "
            sb.append(space)
        }

        return sb.toString()
    }
}