package hello.proxy.app.v2

import hello.proxy.app.v3.OrderRepositoryV3

class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2
) {
    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}