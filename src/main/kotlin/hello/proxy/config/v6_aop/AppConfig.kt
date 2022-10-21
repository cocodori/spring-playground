package hello.proxy.config.v6_aop

import hello.advanced.trace.logtrace.LogTrace
import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import hello.proxy.config.v6_aop.aspect.LogTraceAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AppConfig {

    @Bean
    fun logTraceAspect(logTrace: LogTrace): LogTraceAspect {
        return LogTraceAspect(logTrace)
    }
}