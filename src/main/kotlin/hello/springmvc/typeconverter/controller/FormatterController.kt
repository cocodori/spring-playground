package hello.springmvc.typeconverter.controller

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.NumberFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDateTime

@Controller
class FormatterController {

    @GetMapping("/formatter/edit")
    fun formatterForm(model: Model): String {
        val form = Form(1000000, LocalDateTime.now())

        model["form"] = form

        return "formatter-form"
    }

    @PostMapping("/formatter/edit")
    fun formatterEdit(@ModelAttribute form: Form): String {
        return "formatter-view"
    }

    inner class Form(
        @field:NumberFormat(pattern = "###,###")
        val number: Int,
        @field:DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val localDateTime: LocalDateTime,
    )
}