package hello.advanced.app.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV4(
    private val trace: LogTrace
) {

    fun save(itemId: String) {
        val template = object : AbstractTemplate<Nothing?>(trace) {
            override fun call(): Nothing? {
                if (itemId != "ex")
                    throw IllegalStateException()
                Thread.sleep(1000)
                return null
            }
        }

        template.execute("OrderRepository.save()")
    }

}
