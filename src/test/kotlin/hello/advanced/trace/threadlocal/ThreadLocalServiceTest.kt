package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.ThreadLocalService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ThreadLocalServiceTest {
    private val log = LoggerFactory.getLogger(javaClass)

    private val service: ThreadLocalService = ThreadLocalService()

    @Test
    fun threadLocal() {
        log.info("main start")

        val threadA = Thread { service.logic("userA") }
        threadA.name = "thread-A"

        val threadB = Thread { service.logic("userB") }
        threadA.name = "thread-B"

        threadA.start()
        Thread.sleep(100)
        threadB.start()

        Thread.sleep(2000)

        log.info("main exit")
    }
}