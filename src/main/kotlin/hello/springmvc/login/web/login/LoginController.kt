package hello.springmvc.login.web.login

import hello.springmvc.login.domain.LoginService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
class LoginController(
    private val loginService: LoginService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/login")
    fun loginForm(
        @ModelAttribute("loginForm") form: LoginForm
    ): String {
        return "login/loginForm"
    }

    @PostMapping("/login")
    fun login(
        @Valid @ModelAttribute form: LoginForm,
        bindingResult: BindingResult,
        response: HttpServletResponse,
    ): String {
        if (bindingResult.hasErrors())
            return "login/loginForm"

        val loginMember = loginService.login(form.loginId!!, form.password!!)

        log.info("login? {}", loginMember)

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.")

            return "login/loginForm"
        }

        val idCookie = Cookie("memberId", "${loginMember.id}")
        response.addCookie(idCookie)

        return "redirect:/"
    }

    @PostMapping("/logout")
    fun logout(
        response: HttpServletResponse
    ): String {
        expireCookie(response, "memberId")

        return "redirect:/"
    }

    private fun expireCookie(response: HttpServletResponse, cookieName: String) {
        val cookie = Cookie(cookieName, null)
        cookie.maxAge = 0

        response.addCookie(cookie)

    }


}