package hello.springmvc.login.web

import hello.springmvc.login.domain.member.Member
import hello.springmvc.login.domain.member.MemberRepository
import hello.springmvc.login.web.session.SessionManager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.SessionAttribute
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController(
    private val memberRepository: MemberRepository,
    private val sessionManager: SessionManager,
) {

    @GetMapping
    fun homeLoginV3Spring(
        @SessionAttribute(name = "loginMember", required = false) loginMember: Member?,
        model: Model,
    ): String {
        loginMember ?: return "home"

        model["member"] = loginMember

        return "loginHome"
    }

//    @GetMapping
    fun homeLoginV3(
        request: HttpServletRequest,
        model: Model,
    ): String {
        val session = request.getSession(false)
        session ?: return "home"

        val loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER) as Member?
            ?: return "home"

        model["member"] = loginMember

        return "loginHome"
    }

//    @GetMapping("/")
    fun homeLoginV2(
        request: HttpServletRequest,
        model: Model
    ): String {
        // 세션 관리자에 저장된 회원 정보 조회
        val member = sessionManager.getSession(request) as Member? ?: return "home"

        model["member"] = member
        return "loginHome"
    }

//    @GetMapping("/")
    fun homeLogin(
        @CookieValue(name = "memberId", required = false) memberId: Long?,
        model: Model
    ): String {
        if (memberId == null) {
            return "home"
        }

        //login
        val loginMember = memberRepository.findById(memberId) ?: return "home"
        model["member"] = loginMember

        return "loginHome"
    }
}