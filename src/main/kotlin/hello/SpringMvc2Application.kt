package hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["hello.advanced"])
class SpringMvc2Application

fun main(args: Array<String>) {
	runApplication<SpringMvc2Application>(*args)
}
