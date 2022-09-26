package hello.advanced.app.v0

import hello.advanced.app.v1.OrderRepositoryV1
import org.springframework.stereotype.Service

@Service
class OrderServiceV0(
    private val orderRepositoryV0: OrderRepositoryV1
) {

    fun orderItem(itemId: String) {
        orderRepositoryV0.save(itemId)
    }
}