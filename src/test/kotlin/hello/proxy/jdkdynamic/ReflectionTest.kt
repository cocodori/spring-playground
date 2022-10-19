package hello.proxy.jdkdynamic

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.reflect.Method

class ReflectionTest {
    private val log = LoggerFactory.getLogger(javaClass)

    inner class Hello {
        private val log = LoggerFactory.getLogger(javaClass)

        fun callA(): String {
            log.info("callA")
            return "A"
        }

        fun callB(): String {
            log.info("callA")
            return "B"
        }
    }

    @Test
    fun reflection2() {
        val classHello: Class<*> = Class.forName("hello.proxy.jdkdynamic.ReflectionTest\$Hello")
        val target = Hello()

        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)

        val methodCallB = classHello.getMethod("callB")
        dynamicCall(methodCallB, target)

    }

    private fun dynamicCall(method: Method, target: Hello) {
        log.info("start")
        val result = method.invoke(target)
        log.info("result={}", result)
    }

    @Test
    fun reflection1() {
        val classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest\$Hello")
        val target = Hello()

        // callA 메서드 정보
        val methodCallA = classHello.getMethod("callA")
        val result1 = methodCallA.invoke(target)
        log.info("result1={}", result1)

        //callB 메서드 정보
        val methodCallB = classHello.getMethod("callB")
        val result2 = methodCallB.invoke(target)
        log.info("result2={}", result2)
    }

    @Test
    fun reflection0() {
        val target = Hello()

        //공통 로직 1 시작
        log.info("start")
        val result1 = target.callA()
        log.info("result={}", result1)


        //공통 로직 2 시작
        log.info("start")
        val result2 = target.callB()
        log.info("result={}", result1)
    }
}