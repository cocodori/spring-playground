package hello.proxy.config.v1_proxy.concrete_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.app.v2.OrderServiceV2

class OrderServiceConcreteProxy(
    private val target: OrderServiceV2,
    private val trace: LogTrace
): OrderServiceV2(orderRepository = target.orderRepository) {

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