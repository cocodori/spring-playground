package hello.proxy.postprocessor

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest {
    @Test
    fun basicConfig() {
        val applicationContext: ApplicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)
        val a = applicationContext.getBean("beanA", A::class.java)

        a.helloA()

        assertThrows<NoSuchBeanDefinitionException> {
            applicationContext.getBean(B::class.java)
        }
    }

    @Configuration
    class BasicConfig {
        @Bean(name = ["beanA"])
        fun a(): A {
            return A()
        }
    }

    class A {
        private val log = LoggerFactory.getLogger(javaClass)
        fun helloA() {
            log.info("Hello A")
        }
    }

    class B {
        private val log = LoggerFactory.getLogger(javaClass)

        fun helloB() {
            log.info("Hello B")
        }
    }

}