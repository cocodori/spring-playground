package hello.proxy.app.v2

import hello.proxy.app.v3.OrderServiceV3
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping
@ResponseBody
open class OrderControllerV2(
    val orderService: OrderServiceV2
) {

    @GetMapping("/v2/request")
    open fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    fun noLog(itemId: String): String {
        return "no-log"
    }


}