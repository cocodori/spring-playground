package hello.proxy.advisor

import hello.proxy.common.service.ServiceImpl
import hello.proxy.common.service.ServiceInterface
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {

    @Test
    fun `하나의 프록시, 여러 어드바이저`() {
        // proxy -> advisor2 -> advisor1 -> target
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())

        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvisor(advisor2)
        proxyFactory.addAdvisor(advisor1)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
    }

    @Test
    fun `여러 프록시`() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        // 프록시 1 생성
        val target = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        proxyFactory1.addAdvisor(advisor1)
        val proxy1 = proxyFactory1.proxy as ServiceInterface

        // 프록시 2 생성
        val proxyFactory2 = ProxyFactory(proxy1)
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
        proxyFactory2.addAdvisor(advisor2)
        val proxy2 = proxyFactory2.proxy as ServiceInterface

        proxy2.save()
    }

    inner class Advice1: MethodInterceptor {
        val log = LoggerFactory.getLogger(javaClass)

        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("Advice 1 호출")
            return invocation.proceed()
        }
    }

    inner class Advice2: MethodInterceptor {
        val log = LoggerFactory.getLogger(javaClass)

        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("Advice 2 호출")
            return invocation.proceed()
        }
    }
}