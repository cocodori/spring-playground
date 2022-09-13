package hello.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {

    fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!")
        }
        sleep(1000)
    }

    private fun sleep(mills: Int) {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}