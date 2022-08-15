package hello.springmvc.itemservice.domain.item

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import kotlin.math.max

class Item private constructor(
    var id: Long = -1,

    var itemName: String,

    var price: Int,

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