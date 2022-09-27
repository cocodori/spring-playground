package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class FieldServiceTest {

    private val log = LoggerFactory.getLogger(javaClass)
    private val fieldService = FieldService()

    @Test
    fun field() {
        log.info("main start")

        val threadA = Thread { fieldService.logic("userA") }
        threadA.name = "thread-A"
        val threadB = Thread { fieldService.logic("userB") }
        threadA.name = "thread-B"

        threadA.start()
        Thread.sleep(2000)
        threadB.start()

        Thread.sleep(3000)
        log.info("main exit")
    }
}