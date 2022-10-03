package hello.proxy.config.v1_proxy.concrete_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.app.v2.OrderRepositoryV2

class OrderRepositoryConcreteProxy(
    private val target: OrderRepositoryV2,
    private val trace: LogTrace
): OrderRepositoryV2() {

    override fun save(itemId: String) {
        val status = trace.begin("OrderRepository.save()")

        try {
            target.save(itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

}