package hello.springmvc.itemservice.domain.item

import org.springframework.stereotype.Repository

@Repository
class ItemRepository {

    companion object {
        private val store: MutableMap<Long, Item> = HashMap()
        private var sequence: Long = 0
    }

    fun save(item: Item): Item {
        item.id = ++sequence
        store[item.id] = item

        return item
    }

    fun findById(id: Long): Item? {
        return store[id]
    }

    fun findAll(): List<Item> {
        return store.values.toList()
    }

    fun update(itemId: Long, updateParam: Item) {
        val findItem = findById(itemId)
        findItem?.itemName = updateParam.itemName
        findItem?.price = updateParam.price
        findItem?.quantity = updateParam.quantity
    }

    fun clearStore() {
        store.clear()
    }

}