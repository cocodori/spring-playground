package hello.springmvc.typeconverter

import hello.springmvc.typeconverter.converter.IntegerToStringConverter
import hello.springmvc.typeconverter.converter.IpPortToStringConverter
import hello.springmvc.typeconverter.converter.StringToIntegerConverter
import hello.springmvc.typeconverter.converter.StringToIpPortConverter
import hello.springmvc.typeconverter.formatter.MyNumberFormatter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
//        registry.addConverter(StringToIntegerConverter())
//        registry.addConverter(IntegerToStringConverter())
        registry.addConverter(StringToIpPortConverter())
        registry.addConverter(IpPortToStringConverter())

        registry.addFormatter(MyNumberFormatter())
    }
}