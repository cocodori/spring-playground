package hello

import hello.proxy.config.v1_proxy.ConcreteProxyConfig
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

