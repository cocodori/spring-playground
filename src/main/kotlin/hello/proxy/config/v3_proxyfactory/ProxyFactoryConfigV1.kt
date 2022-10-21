package hello.proxy.config.v3_proxyfactory

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.app.v1.*
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class ProxyFactoryConfigV1 {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val orderControllerV1 = OrderControllerV1Impl(orderServiceV1(logTrace))
        val proxyFactory = ProxyFactory(orderControllerV1)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        val proxy = proxyFactory.proxy as OrderControllerV1
        log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, orderControllerV1.javaClass)
        return proxy
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderServiceV1 = OrderServiceV1Impl(orderRepositoryV1(logTrace))
        val proxyFactory = ProxyFactory(orderServiceV1)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        val proxy = proxyFactory.proxy as OrderServiceV1
        log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, orderServiceV1.javaClass)
        return proxy
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepositoryV1 = OrderRepositoryV1Impl()
        val factory = ProxyFactory(orderRepositoryV1)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderRepositoryV1
        log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, orderRepositoryV1.javaClass)
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