package hello.springmvc.upload.domain

import org.springframework.stereotype.Repository

@Repository
class ItemRepo {

    private val store = mutableMapOf<Long, Item>()
    private var sequence = 0L

    fun save(item: Item): Item {
        item.id = ++sequence
        store[item.id!!] = item

        return item
    }

    fun findById(id: Long) = store[id]
}