package hello.springmvc.typeconverter.controller

import hello.springmvc.typeconverter.type.IpPort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ConverterController {

    @GetMapping("/converter-view")
    fun convertView(model: Model): String {
        model["number"] = 10000
        model["ipPort"] = IpPort("127.0.0.1", 8080)

        return "converter-view"
    }

    @GetMapping("/converter/edit")
    fun converterForm(
        model: Model
    ): String {
        val ipPort = IpPort("127.0.0.1", 8080)
        val form = Form(ipPort)

        model["form"] = form

        return "converter-form"
    }

    @PostMapping("/converter/edit")
    fun converterEdit(
        @ModelAttribute form: Form,
        model: Model
    ): String {
        val ipPort = form.ipPort
        model["ipPort"] = ipPort
        return "converter-view"
    }

    data class Form(
        val ipPort: IpPort
    )
}