package hello.proxy.app.v1

open class OrderRepositoryV1Impl: OrderRepositoryV1 {
    override fun save(itemId: String) {
        if (itemId == "ex")
            throw IllegalStateException()
        Thread.sleep(1000)
    }
}