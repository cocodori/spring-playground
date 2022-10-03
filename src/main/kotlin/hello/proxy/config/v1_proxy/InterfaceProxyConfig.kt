package hello.proxy.config.v1_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.app.v1.*
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterfaceProxyConfig {

    @Bean
    fun orderController(trace: LogTrace): OrderControllerV1 {
        val orderControllerV1Impl = OrderControllerV1Impl(orderService(trace))

        return OrderControllerInterfaceProxy(orderControllerV1Impl, trace)
    }

    @Bean
    fun orderService(trace: LogTrace): OrderServiceV1 {
        val orderServiceV1Impl = OrderServiceV1Impl(orderRepository(trace))
        return OrderServiceInterfaceProxy(orderServiceV1Impl, trace)
    }

    @Bean
    fun orderRepository(trace: LogTrace): OrderRepositoryV1 {
        val repositoryV1Impl = OrderRepositoryV1Impl()
        return OrderRepositoryInterfaceProxy(repositoryV1Impl, trace)
    }

    @Bean
    fun trace(): LogTrace = ThreadLocalLogTrace()
}