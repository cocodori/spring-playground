package hello.advanced.app.v3

import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3(
    private val trace: LogTrace
) {

    fun save(itemId: String) {
        val status = trace.begin("OrderRepository.save()")

        try {
            if (itemId == "ex")
                throw IllegalStateException()

            Thread.sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }


}
