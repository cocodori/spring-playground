package hello.proxy.common.service

import org.slf4j.LoggerFactory

class ServiceImpl: ServiceInterface {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun save() {
        log.info("save 호출")
    }

    override fun find() {
        log.info("find 호출")
    }
}