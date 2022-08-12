package hello.springmvc.itemservice.domain.item

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ItemRepositoryTest {

    val itemRepository = ItemRepository()

    @AfterEach
    fun clean() {
        itemRepository.clearStore()
    }

    @Test
    fun save() {
        //given
        val item = Item("itemA", 10000, 10)
        val savedItem = itemRepository.save(item)
        val itemId = savedItem.id

        //when
        val updateParam = Item("item2", 20000, 30)
        itemRepository.update(itemId, updateParam)

        val findItem = itemRepository.findById(itemId)

        //then
        assertThat(findItem?.itemName).isEqualTo(updateParam.itemName)
        assertThat(findItem?.price).isEqualTo(updateParam.price)
        assertThat(findItem?.quantity).isEqualTo(updateParam.quantity)
    }
}