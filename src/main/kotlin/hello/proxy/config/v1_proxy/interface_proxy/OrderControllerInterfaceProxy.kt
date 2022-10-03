package hello.proxy.config.v1_proxy.interface_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.app.v1.OrderControllerV1

class OrderControllerInterfaceProxy(
    private val target: OrderControllerV1,
    private val trace: LogTrace
): OrderControllerV1 {
    override fun request(itemId: String): String {
        val status = trace.begin("OrderService.orderItem()")

        try {
            val result = target.request(itemId)
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }


    }

    override fun noLog(): String = target.noLog()

}
