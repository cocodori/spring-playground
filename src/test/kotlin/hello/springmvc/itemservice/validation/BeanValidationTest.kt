package hello.springmvc.itemservice.validation

import hello.springmvc.itemservice.domain.item.Item
import org.junit.jupiter.api.Test
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.ValidatorFactory

class BeanValidationTest {

    @Test
    fun beanValidation() {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        val validator = factory.validator

        val item = Item(
            itemName = "   ",
            price = 0,
            quantity = 10000
        )

        val violations: Set<ConstraintViolation<Item>> = validator.validate(item)

        for (violation in violations) {
            println("violation=$violation")
            println("violation.message=${violation.message}")
        }
    }
}