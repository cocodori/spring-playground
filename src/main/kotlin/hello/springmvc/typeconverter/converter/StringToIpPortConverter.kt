package hello.springmvc.typeconverter.converter

import hello.springmvc.typeconverter.type.IpPort
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter

class StringToIpPortConverter: Converter<String, IpPort> {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun convert(source: String): IpPort {
        log.info("convert source={}", source)
        val split = source.split(":")
        val ip = split[0]
        val port = split[1].toInt()

        return IpPort(ip, port)
    }


}