package hello.springmvc.login.web.login

import hello.springmvc.login.domain.LoginService
import hello.springmvc.login.web.SessionConst
import hello.springmvc.login.web.session.SessionManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import javax.websocket.Session

@Controller
class LoginController(
    private val loginService: LoginService,
    private val sessionManager: SessionManager,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/login")
    fun loginForm(
        @ModelAttribute("loginForm") form: LoginForm
    ): String {
        return "login/loginForm"
    }

    @PostMapping("/login")
    fun loginV4(
        @Valid @ModelAttribute form: LoginForm,
        bindingResult: BindingResult,
        @RequestParam(defaultValue = "/") redirectURL: String,
        request: HttpServletRequest
    ): String {
        if (bindingResult.hasErrors())
            return "login/loginForm"

        val loginMember = loginService.login(form.loginId!!, form.password!!)
        log.info("login? {}", loginMember)

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.")
            return "login/loginForm"
        }

        val session = request.session
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember)

        return "redirect:$redirectURL"
    }

//    @PostMapping("/login")
    fun loginV3(
        @Valid @ModelAttribute form: LoginForm,
        bindingResult: BindingResult,
        request: HttpServletRequest
    ): String {
        if (bindingResult.hasErrors())
            return "login/loginForm"

        val loginMember = loginService.login(form.loginId!!, form.password!!)
        log.info("login? {}", loginMember)

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.")
            return "login/loginForm"
        }

        val session = request.session
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember)

        return "redirect:/"
    }

    @PostMapping("/logout")
    fun logoutV3(request: HttpServletRequest): String {
        request.getSession(false)?.invalidate()
        return "redirect:/"
    }

//    @PostMapping("login")
    fun loginV2(
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

        //로그인 성공 처리
        sessionManager.createSession(loginMember, response)

        return "redirect:/"
    }

//    @PostMapping("/logout")
    fun logoutV2(
        request: HttpServletRequest
    ): String {
        sessionManager.expire(request)
        return "redirect:/"
    }

//    @PostMapping("/login")
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

//    @PostMapping("/logout")
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