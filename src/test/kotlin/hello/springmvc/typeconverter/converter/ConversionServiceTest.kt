package hello.springmvc.typeconverter.converter

import hello.springmvc.typeconverter.type.IpPort
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.convert.support.DefaultConversionService

class ConversionServiceTest {

    @Test
    fun conversionService() {
        val conversionService = DefaultConversionService()

        // 등록
        conversionService.addConverter(StringToIntegerConverter())
        conversionService.addConverter(IntegerToStringConverter())
        conversionService.addConverter(StringToIpPortConverter())
        conversionService.addConverter(IpPortToStringConverter())

        // 사용
        assertThat(conversionService.convert("10", Int::class.java))
            .isEqualTo(10)
        assertThat(conversionService.convert(10, String::class.java))
            .isEqualTo("10")

        val ipPort = conversionService.convert("127.0.0.1:8080", IpPort::class.java)
        assertThat(ipPort).isEqualTo(IpPort("127.0.0.1", 8080))

        val ipPortString = conversionService.convert(ipPort, String::class.java)
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080")
    }
}