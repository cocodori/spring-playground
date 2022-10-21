package hello.proxy.config.v3_proxyfactory

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.app.v1.*
import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV2 {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val orderControllerV2 = OrderControllerV2(orderServiceV2(logTrace))
        val proxyFactory = ProxyFactory(orderControllerV2)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        val proxy = proxyFactory.proxy as OrderControllerV2
        log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, orderControllerV2.javaClass)
        return proxy
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val orderServiceV2 = OrderServiceV2(orderRepositoryV2(logTrace))
        val proxyFactory = ProxyFactory(orderServiceV2)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        val proxy = proxyFactory.proxy as OrderServiceV2
        log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, orderServiceV2.javaClass)
        return proxy
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepositoryV2 = OrderRepositoryV2()
        val factory = ProxyFactory(orderRepositoryV2)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderRepositoryV2
        log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, orderRepositoryV2.javaClass)
        return proxy
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")

        //advice
        val advice = LogTraceAdvice(logTrace)

        return DefaultPointcutAdvisor(pointcut, advice)
    }

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()



}