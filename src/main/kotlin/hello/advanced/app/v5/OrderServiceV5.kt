package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val orderRepository: OrderRepositoryV5,
    private val trace: LogTrace
) {
    private val template = TraceTemplate(trace)

    fun orderItem(itemId: String) {
        template.execute("OrderService.orderItem()") {
            orderRepository.save(itemId)
        }
    }

}
