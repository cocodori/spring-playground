package hello.springmvc.typeconverter.formatter

import hello.springmvc.typeconverter.converter.IpPortToStringConverter
import hello.springmvc.typeconverter.converter.StringToIpPortConverter
import hello.springmvc.typeconverter.type.IpPort
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.format.support.DefaultFormattingConversionService

class FormattingConversionServiceTest {

    @Test
    fun formattingConversionService() {
        val conversionService = DefaultFormattingConversionService()

        //add converter
        conversionService.addConverter(StringToIpPortConverter())
        conversionService.addConverter(IpPortToStringConverter())

        //add formatter
        conversionService.addFormatter(MyNumberFormatter())

        //use converter
        val ipPort = conversionService.convert("127.0.0.1:8080", IpPort::class.java)
        assertThat(ipPort).isEqualTo(IpPort("127.0.0.1", 8080))

        //use formatter
        assertThat(conversionService.convert(1000, String::class.java))
            .isEqualTo("1,000")

        assertThat(conversionService.convert("1,000", Long::class.java))
            .isEqualTo(1000L)

    }
}