package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory

class ThreadLocalLogTrace: LogTrace {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X--"
    }

    private val traceIdHolder = ThreadLocal<TraceId>()

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception? = null) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTime = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                traceId.id,
                addSpace(COMPLETE_PREFIX, traceId.level),
                status.message,
                resultTime
            )
        } else {
            log.info("[{}] {}{} time={}ms, ex={}",
                traceId.id,
                addSpace(EX_PREFIX, traceId.level),
                status.message,
                resultTime,
                e.toString()
            )
        }

        releaseTraceId()
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(traceId.createPreviousId())
        }
    }

    private fun syncTraceId() {
        val traceId = traceIdHolder.get()

        if (traceId == null)
            traceIdHolder.set(TraceId())
        else
            traceIdHolder.set(traceId.createNextId())
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