package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class ThreadLocalService {

    private val log = LoggerFactory.getLogger(javaClass)
    private val nameStore = ThreadLocal<String>()

    fun logic(name: String): String {
        log.info("저장 name=={} -> nameStore={}", name, nameStore.get())
        nameStore.set(name)
        Thread.sleep(1000)
        log.info("조회 nameStore={}", nameStore.get())
        return nameStore.get()
    }
}