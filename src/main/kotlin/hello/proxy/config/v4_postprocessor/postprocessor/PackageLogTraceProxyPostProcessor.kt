package hello.proxy.config.v4_postprocessor.postprocessor

import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTraceProxyPostProcessor(
    private val basePackage: String,
    private val advisor: Advisor
): BeanPostProcessor {

    val log = LoggerFactory.getLogger(javaClass)

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        log.info("param beanName={}, bean={}", beanName, bean.javaClass)

        // 프록시 적용 대상 여부 체크
        // 프록시 적용 대상이 아니면 원본 그대로 반환
        val packageName = bean.javaClass.packageName
        if (!packageName.startsWith(basePackage)) return bean

        // 프록시 대상이면 프록시를 만들어서 반환
        val proxyFactory = ProxyFactory(bean)
        proxyFactory.addAdvisor(advisor)

        val proxy = proxyFactory.proxy

        log.info("create proxy: target={}, proxy={}", bean.javaClass, proxy.javaClass)

        return proxy
    }
}