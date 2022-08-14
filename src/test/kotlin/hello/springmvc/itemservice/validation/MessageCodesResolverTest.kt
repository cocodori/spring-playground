package hello.springmvc.itemservice.validation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.validation.DefaultMessageCodesResolver
import org.springframework.validation.MessageCodesResolver

class MessageCodesResolverTest {

    val log = LoggerFactory.getLogger(javaClass)
    val codesResolver: MessageCodesResolver = DefaultMessageCodesResolver()

    @Test
    fun messageCodesResolverObject() {
        val messageCodes = codesResolver.resolveMessageCodes("required", "item")

        log.info { "messageCodes=${messageCodes.contentToString()}" }

        assertThat(messageCodes).containsExactly("required.item", "required")
    }

    @Test
    fun messageCodeResolverField() {
        val messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String::class.java)

        log.info { "messageCodes=${messageCodes.contentToString()}" }

        assertThat(messageCodes).containsExactly(
            "required.item.itemName",
            "required.itemName",
            "required.java.lang.String",
            "required"
        )
    }
}