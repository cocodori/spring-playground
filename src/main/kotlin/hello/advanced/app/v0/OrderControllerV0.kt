package hello.advanced.app.v0

import hello.advanced.app.v1.OrderServiceV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV0(
    private val orderServiceV0: OrderServiceV1
) {
    @GetMapping("/v0/request")
    fun request(itemId: String): String {
        orderServiceV0.orderItem(itemId)

        return "ok"
    }
}