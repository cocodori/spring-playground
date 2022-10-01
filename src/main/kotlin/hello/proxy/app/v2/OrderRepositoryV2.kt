package hello.proxy.app.v2

class OrderRepositoryV2 {

    fun save(itemId: String) {
        if (itemId == "ex")
            throw IllegalStateException()

        Thread.sleep(1000)
    }
}