package hello.aop

import hello.aop.order.OrderRepository
import hello.aop.order.OrderService
import hello.aop.order.aop.AspectV1
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(AspectV1::class)
@SpringBootTest
class AopTest {
    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var orderRepository: OrderRepository

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService))
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository))
    }

    @Test
    fun success() {
        orderService.orderItem("itemId")
    }

    @Test
    fun exception() {
        assertThatThrownBy { orderService.orderItem("ex") }
            .isInstanceOf(IllegalStateException::class.java)
    }
}