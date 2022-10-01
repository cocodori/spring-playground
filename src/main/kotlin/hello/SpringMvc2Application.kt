package hello

import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@Import(AppV1Config::class, AppV2Config::class)
@SpringBootApplication
@ComponentScan(basePackages = ["hello.proxy"])
class SpringMvc2Application

fun main(args: Array<String>) {
	runApplication<SpringMvc2Application>(*args)
}
