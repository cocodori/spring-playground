package hello.proxy.config.v1_proxy.interface_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.app.v1.OrderRepositoryV1

class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val trace: LogTrace
): OrderRepositoryV1 {
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