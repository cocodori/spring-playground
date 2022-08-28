package hello.springmvc.typeconverter.formatter

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class MyNumberFormatterTest {
    val formatter = MyNumberFormatter()

    @Test
    fun parse() {
        val result = formatter.parse("1,000", Locale.KOREA)

        assertThat(result).isEqualTo(1000L)
    }

    @Test
    fun print() {
        val result = formatter.print(1000, Locale.KOREA)

        assertThat(result).isEqualTo("1,000")
    }
}