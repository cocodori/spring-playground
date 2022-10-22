package hello.aop.order

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun orderItem(itemId: String) {
        log.info("[orderService] 실행")

        orderRepository.save(itemId)
    }

}