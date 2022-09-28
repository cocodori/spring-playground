package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV5(
    private val orderService: OrderServiceV5,
    private val trace: LogTrace
) {

    private val template = TraceTemplate(trace)

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        template.execute("OrderController.request()") {
            orderService.orderItem(itemId)
        }

        return "ok"
    }


}