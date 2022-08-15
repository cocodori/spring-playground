package hello.springmvc.itemservice.domain.item

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import kotlin.math.max

class Item private constructor(
    @field:NotNull(groups = [UpdateCheck::class])
    var id: Long = -1,

    @field:NotBlank(groups = [SaveCheck::class, UpdateCheck::class])
    var itemName: String,

    @field:NotNull(groups = [SaveCheck::class, UpdateCheck::class])
    @field:Range(min = 1000, max = 1000000, groups = [SaveCheck::class, UpdateCheck::class])
    var price: Int,

    @field:NotNull(groups = [SaveCheck::class, UpdateCheck::class])
    @field:Max(9999, groups = [SaveCheck::class])
    var quantity: Int,
) {

    constructor(
        itemName: String,
        price: Int,
        quantity: Int
    ): this(
        id = -1,
        itemName = itemName,
        price = price,
        quantity = quantity
    )
}