package hello.springmvc.itemservice.domain.item.basic

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import hello.springmvc.itemservice.domain.item.SaveCheck
import hello.springmvc.itemservice.domain.item.UpdateCheck
import hello.springmvc.itemservice.web.validation.ItemValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/basic/items/v3")
class ValidationItemControllerV3(
    private val itemValidator: ItemValidator,
    private val itemRepository: ItemRepository,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model["items"] = items

        return "validation/v3/items"
    }

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)!!
        model["item"] = item

        return "validation/v3/item"
    }

    @GetMapping("/add")
    fun addForm(model: Model): String {
        model["item"] = Item("", 0, 0)

        return "validation/v3/addForm"
    }

    @PostMapping("/add")
    fun addItem(
        @Validated(SaveCheck::class) @ModelAttribute item: Item,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", arrayOf(10000, resultPrice), null)
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult)
            return "validation/v3/addForm"
        }

        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v3/items/{itemId}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(@PathVariable itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)!!
        model.addAttribute("item", item)
        return "validation/v3/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun edit(
        @PathVariable itemId: Long,
        @ModelAttribute @Validated(UpdateCheck::class) item: Item,
        bindingResult: BindingResult
    ): String {
        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", arrayOf(10000, resultPrice), null)
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult)
            return "validation/v3/editForm"
        }
        itemRepository.update(itemId, item)
        return "redirect:/validation/v3/items/{itemId}"
    }
}