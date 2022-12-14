package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class FieldService {
    private val log = LoggerFactory.getLogger(javaClass)

    private var nameStore = ""

    fun logic(name: String): String {
        log.info("์ ์ฅ name={} -> namespace={}", name, nameStore)

        nameStore = name

        Thread.sleep(1000)

        log.info("์กฐํ nameStore={}", nameStore)

        return nameStore
    }
}