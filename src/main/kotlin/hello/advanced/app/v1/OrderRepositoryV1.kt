package hello.advanced.app.v1

import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV1(
    private val trace: HelloTraceV1
) {

    fun save(itemId: String) {

        val status = trace.begin("OrderRepository.save()")

        try {
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!")
            }
            sleep(1000)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    private fun sleep(mills: Int) {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}