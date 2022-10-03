package hello.proxy.config.v1_proxy.concrete_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.app.v2.OrderControllerV2

class OrderControllerConcreteProxy(
    private val target: OrderControllerV2,
    private val trace: LogTrace
): OrderControllerV2(target.orderService) {

    override fun request(itemId: String): String {
        val status = trace.begin("OrderController.request(itemId = $itemId)")

        try {
            val result = target.request(itemId)
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}