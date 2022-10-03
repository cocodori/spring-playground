package hello.proxy.app.v2

open class OrderRepositoryV2 {

    open fun save(itemId: String) {
        if (itemId == "ex")
            throw IllegalStateException()

        Thread.sleep(1000)
    }
}