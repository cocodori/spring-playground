package hello.proxy.config.v1_proxy

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {

    @Bean
    fun orderControllerV2(trace: LogTrace): OrderControllerV2 {
        return OrderControllerConcreteProxy(target = OrderControllerV2(orderServiceV2(trace)), trace = trace)
    }

    @Bean
    fun orderServiceV2(trace: LogTrace): OrderServiceV2 {
        val orderServiceV2 = OrderServiceV2(orderRepositoryV2(trace))
        return OrderServiceConcreteProxy(orderServiceV2, trace)
    }

    @Bean
    fun orderRepositoryV2(trace: LogTrace): OrderRepositoryV2 {
        val orderRepositoryV2 = OrderRepositoryV2()
        return OrderRepositoryConcreteProxy(target = orderRepositoryV2, trace = trace)
    }

    @Bean
    fun trace(): LogTrace = ThreadLocalLogTrace()
}