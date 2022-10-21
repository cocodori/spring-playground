package hello

import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@Import(ProxyFactoryConfigV2::class)
@SpringBootApplication
@ComponentScan(basePackages = ["hello.proxy.*"])
class SpringMvc2Application

fun main(args: Array<String>) {
	runApplication<SpringMvc2Application>(*args)
}

