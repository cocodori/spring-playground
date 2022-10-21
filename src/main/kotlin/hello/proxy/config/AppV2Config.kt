package hello.proxy.config

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.app.v3.OrderControllerV3
import hello.proxy.app.v3.OrderServiceV3
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppV2Config {

    @Bean
    fun orderControllerV2() = OrderControllerV2(orderServiceV2())

    @Bean
    fun orderServiceV2() = OrderServiceV2(orderRepositoryV2())

    @Bean
    fun orderRepositoryV2() = OrderRepositoryV2()

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()
}