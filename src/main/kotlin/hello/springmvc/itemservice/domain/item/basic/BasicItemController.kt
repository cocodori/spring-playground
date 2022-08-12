package hello.springmvc.itemservice.domain.item.basic

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.annotation.PostConstruct

@Controller
@RequestMapping("/basic/items")
class BasicItemController(
    private val itemRepository: ItemRepository
) {

    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model["items"] = items

        return "basic/items"
    }

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)
        item?.let { model["item"] = itemId }

        return "basic/item"
    }

    @GetMapping("/add")
    fun addForm(): String {
        return "basic/addForm"
    }

//    @PostMapping("/add")
    fun addItemV1(
        @RequestParam itemName: String,
        @RequestParam price: Int,
        @RequestParam quantity: Int,
        model: Model
    ): String {
        val item = Item(itemName, price, quantity)

        itemRepository.save(item)

        model.addAttribute("item", item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV2(
        @ModelAttribute item: Item,
        model: Model
    ): String {
        itemRepository.save(item)

        return "basic/item"
    }

    @PostMapping("/addV4")
    fun addItemV4(item: Item): String {
        itemRepository.save(item)

        return "basic/item"
    }

    @PostMapping("/add")
    fun addItemV6(
        item: Item,
        redirectAttributes: RedirectAttributes,
    ): String {
        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/basic/items/{itemId}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(
        @PathVariable itemId: Long,
        model: Model,
    ): String {
        val item = itemRepository.findById(itemId)!!
        model["item"] = item

        return "basic/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun edit(
        @PathVariable itemId: Long,
        @ModelAttribute item: Item,
    ): String {
        itemRepository.update(itemId, item)

        return "redirect:/basic/items/{itemId}"
    }



    @PostConstruct
    fun init() {
        itemRepository.save(Item("testA", 10000, 10))
        itemRepository.save(Item("testB", 20000, 20))
    }
}