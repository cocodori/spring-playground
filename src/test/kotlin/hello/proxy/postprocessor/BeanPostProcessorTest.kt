package hello.proxy.postprocessor

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanPostProcessorTest {
    @Test
    fun postProcessor() {
        val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)
        val b = applicationContext.getBean("beanA", B::class.java)
        b.helloB()

        assertThrows<NoSuchBeanDefinitionException> {
            applicationContext.getBean(A::class.java)
        }

    }

    @Configuration
    class BeanPostProcessorConfig {
        @Bean(name = ["beanA"])
        fun a(): A = A()

        @Bean
        fun helloPostProcessor(): AToBPostProcessor {
            return AToBPostProcessor()
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

    class AToBPostProcessor: BeanPostProcessor {
        private val log = LoggerFactory.getLogger(javaClass)

        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
            log.info("beanName={}, bean={}", beanName, bean)

            if (bean is A) return B()

            return bean
        }
    }
}