package hello.springmvc.typeconverter.converter

import hello.springmvc.typeconverter.type.IpPort
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter

class IpPortToStringConverter: Converter<IpPort, String> {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun convert(source: IpPort): String? {
        log.info("convert source={}", source)
        return "${source.ip}:${source.port}"
    }


}