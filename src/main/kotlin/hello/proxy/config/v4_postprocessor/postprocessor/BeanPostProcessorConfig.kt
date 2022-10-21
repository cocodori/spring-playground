package hello.proxy.config.v4_postprocessor.postprocessor

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class BeanPostProcessorConfig {

    @Bean
    fun logTraceProxyPostProcessor(logTrace: LogTrace): PackageLogTraceProxyPostProcessor {
        return PackageLogTraceProxyPostProcessor("hello.proxy.app", getAdvisor(logTrace))
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")

        val advice = LogTraceAdvice(logTrace)

        return DefaultPointcutAdvisor(pointcut, advice)
    }
}