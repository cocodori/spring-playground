package hello.proxy.config.v2_dynamicproxy

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.app.v1.*
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyBasicConfig {

    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val orderControllerV1: OrderControllerV1 = OrderControllerV1Impl(orderService = orderServiceV1(logTrace))

        return Proxy.newProxyInstance(
            OrderControllerV1::class.java.classLoader,
            arrayOf(OrderControllerV1::class.java),
            LogTraceBasicHandler(orderControllerV1, logTrace)
        ) as OrderControllerV1
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderServiceV1: OrderServiceV1 = OrderServiceV1Impl(orderRepository = orderRepositoryV1(logTrace))

        return Proxy.newProxyInstance(
            OrderServiceV1::class.java.classLoader,
            arrayOf(OrderServiceV1::class.java),
            LogTraceBasicHandler(orderServiceV1, logTrace)
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepositoryV1: OrderRepositoryV1 = OrderRepositoryV1Impl()

        return Proxy.newProxyInstance(
            OrderRepositoryV1::class.java.classLoader,
            arrayOf(OrderRepositoryV1::class.java),
            LogTraceBasicHandler(orderRepositoryV1, logTrace)
        ) as OrderRepositoryV1
    }

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()
}