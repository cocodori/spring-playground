package hello.springmvc.itemservice.web.validation.form

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ItemSaveForm(
    @field:NotBlank
    val itemName: String,

    @field:NotNull
    @field:Range(min = 1000, max = 1000000)
    val price: Int,

    @field:NotNull
    @field:Max(value = 9999)
    val quantity: Int,
)