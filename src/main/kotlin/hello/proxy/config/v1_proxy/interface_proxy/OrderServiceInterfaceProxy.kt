package hello.proxy.config.v1_proxy.interface_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.app.v1.OrderServiceV1

class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val trace: LogTrace
): OrderServiceV1 {
    override fun orderItem(itemId: String) {
        val status = trace.begin("OrderService.orderItem()")

        try {
            target.orderItem(itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}