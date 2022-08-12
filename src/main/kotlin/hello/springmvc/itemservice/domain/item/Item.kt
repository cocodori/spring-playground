package hello.springmvc.itemservice.domain.item

class Item private constructor(
    var id: Long,
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