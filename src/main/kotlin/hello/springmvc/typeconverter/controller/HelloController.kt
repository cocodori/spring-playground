package hello.springmvc.typeconverter.controller

import hello.springmvc.typeconverter.type.IpPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class HelloController {

    @GetMapping("/hello-v1")
    fun helloV1(
        request: HttpServletRequest
    ): String {
        val data = request.getParameter("data")
        val intValue = data.toInt()
        println("intValue=$intValue")
        return "ok"
    }

    @GetMapping("/hello-v2")
    fun helloV2(
        @RequestParam data: Int
    ): String {
        println("data=$data")
        return "ok2"
    }

    @GetMapping("/ip-port")
    fun ipPort(@RequestParam ipPort: IpPort): String {
        println("ipPort IP: ${ipPort.ip}")
        println("ipPort Port: ${ipPort.port}")

        return "ok3"
    }
}