package hello.springmvc.itemservice.web.validation.form

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ItemUpdateForm(
    @field:NotNull
    val id: Long,

    @field:NotBlank
    val itemName: String,

    @field:NotNull
    @field:Range(min = 1000, max = 1000000)
    val price: Int,

    val quantity: Int,
)