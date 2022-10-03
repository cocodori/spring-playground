package hello

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import hello.proxy.config.v1_proxy.ConcreteProxyConfig
import hello.proxy.config.v1_proxy.InterfaceProxyConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@Import(ConcreteProxyConfig::class)
@SpringBootApplication
@ComponentScan(basePackages = ["hello.proxy"])
class SpringMvc2Application

fun main(args: Array<String>) {
	runApplication<SpringMvc2Application>(*args)
}

