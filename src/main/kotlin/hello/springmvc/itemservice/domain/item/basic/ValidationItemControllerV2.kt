package hello.springmvc.itemservice.domain.item.basic

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import hello.springmvc.itemservice.web.validation.ItemValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.util.StringUtils
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RequestMapping("/basic/items/v2")
@Controller
class ValidationItemControllerV2(
    private val itemValidator: ItemValidator,
    private val itemRepository: ItemRepository,
) {

    private val log = LoggerFactory.getLogger(javaClass)


    @PostMapping("add")
    fun addItemV5(
        @ModelAttribute item: Item,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        itemValidator.validate(item, bindingResult)

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult)

            return "validation/v2/addForm"
        }

        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v2/items/{itemId}"
    }

//    @PostMapping("/add")
    fun addItemV4(
        @ModelAttribute item: Item,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        log.info("objectName={}", bindingResult.objectName)
        log.info("target={}", bindingResult.target)

        if (item.itemName.isNullOrBlank()) {
            bindingResult.rejectValue("itemName", "required")
        }

        if (item.price == null || item.price < 1000 || item.price > 1000000) {
            bindingResult.rejectValue("price", "range", arrayOf(1000, 1000000), null)
        }

        if (item.quantity == null || item.quantity > 10000) {
            bindingResult.rejectValue("quantity", "max", arrayOf(9999), null)
        }

        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", arrayOf(10000, resultPrice), null)
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult)
            return "validation/v2/addForm"
        }

        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v2/items/{itemId}"
    }



//    @PostMapping("/add")
    fun addItemV3(
        @ModelAttribute item: Item,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {

        log.info("objectName={}", bindingResult.objectName)
        log.info("target={}", bindingResult.target)

        if (!StringUtils.hasText(item.itemName)) {
            bindingResult.addError(
                FieldError(
                    "item",
                    "itemName",
                    item.itemName,
                    false,
                    arrayOf("required.item.itemName"),
                    null,
                    null
                )
            )
        }

        if (item.price == null || item.price < 1000 || item.price > 1000000) {
            bindingResult.addError(
                FieldError(
                    "item",
                    "price",
                    item.price,
                    false,
                    arrayOf("range.item.price"),
                    arrayOf(1000, 10000),
                    null,
                )
            )
        }

        if (item.quantity == null || item.quantity > 10000) {
            bindingResult.addError(
                FieldError(
                    "item",
                    "quantity",
                    item.quantity,
                    false,
                    arrayOf("max.item.quantity"),
                    arrayOf(9999),
                    null
                )
            )
        }

        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                bindingResult.addError(
                    ObjectError(
                        "item",
                        arrayOf("totalPriceMin"),
                        arrayOf(10000, resultPrice),
                        null
                    )
                )
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult)
            return "validation/v2/addForm"
        }

        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v2/items/{itemId}"
    }

    //    @PostMapping("/add")
    fun addItemV2(
        @ModelAttribute item: Item,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {
        if (!StringUtils.hasText(item.itemName)) {
            bindingResult.addError(
                FieldError(
                    "item",
                    "itemName",
                    item.itemName,
                    false,
                    null,
                    null,
                    "상점 이름은 필수입니다."
                )
            )
        }

        if (item.price == null || item.price < 1000 || item.price > 1000000) {
            bindingResult.addError(
                FieldError(
                    "item",
                    "price",
                    item.price,
                    false,
                    null,
                    null,
                    "가격은 1,000~1,000,000까지 허용합니다."
                )
            )
        }

        if (item.quantity == null || item.quantity > 10000) {
            bindingResult.addError(
                FieldError(
                    "item",
                    "quantity",
                    item.quantity,
                    false,
                    null,
                    null,
                    "수량은 최대 9,999까지 허용합니다."
                )
            )
        }

        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                bindingResult.addError(
                    ObjectError(
                        "item",
                        null,
                        null,
                        "가격 * 수량의 합은 1,0000 이상이어 한다. 현재 값=$resultPrice"
                    )
                )
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult)
            return "validation/v2/addForm"
        }

        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v2/items/{itemId}"
    }

//    @PostMapping("/add")
    fun addItem(
        @ModelAttribute item: Item,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        //검증 로직
        if (!StringUtils.hasText(item.itemName)) {
            bindingResult.addError(
                FieldError("item", "price","상품 이름은 필수입니다."))
        }

        if (item.price == null && item.price < 1000 || item.price > 1000000) {
            bindingResult.addError(
                FieldError("item", "price", "가격은 1,000~1,000,000까지 허용합니다.")
            )
        }

        if (item.quantity == null || item.quantity >= 9999) {
            bindingResult.addError(
                FieldError(
                    "item", "quantity", "수량은 최대 9,999까지 허용합니다."
                )
            )
        }

        //전체 예외
        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                bindingResult.addError(
                    ObjectError(
                        "item",
                        "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 $resultPrice"
                    )
                )
            }
        }

        //검증에 실패할 경우 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult)
            return "validation/v2/addForm"
        }

        //성공 로직
        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v2/items/{itemId}"
    }

}