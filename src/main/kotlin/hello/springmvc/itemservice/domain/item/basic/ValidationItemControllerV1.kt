package hello.springmvc.itemservice.domain.item.basic

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RequestMapping("/basic/items/v1")
@Controller
class ValidationItemControllerV1(
    private val itemRepository: ItemRepository
) {

    @PostMapping("/add")
    fun addItem(
        @ModelAttribute item: Item,
        redirectAttributes: RedirectAttributes,
        model: Model
    ): String {
        //검증 오류 보관
        val errors = mutableMapOf<String, String>()

        //검증 로직
        if (!StringUtils.hasText(item.itemName)) {
            errors["itemName"] = "상품 이름은 필수입니다."
        }

        if (item.price == null && item.price < 1000 || item.price > 1000000) {
            errors["price"] = "가격은 1,000~1,000,000까지 허용합니다."
        }

        if (item.quantity == null || item.quantity >= 9999) {
            errors["quantity"] = "수량은 최대 9,999까지 허용합니다."
        }

        //특정 필드가 아닌 복합 role 검증
        if (item.price != null && item.quantity != null) {
            val resultPrice = item.price * item.quantity
            if (resultPrice < 10000) {
                errors["globalError"] = "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 $resultPrice"
            }
        }

        //검증에 실패할 경우 다시 입력 폼으로
        if (errors.isNotEmpty()) {
            model["errors"] = errors
            return "validation/v1/addForm"
        }

        //성공 로직
        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true)

        return "redirect:/validation/v1/items/{itemId}"
    }

}