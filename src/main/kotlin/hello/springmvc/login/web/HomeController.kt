package hello.springmvc.login.web

import hello.springmvc.login.domain.member.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val memberRepository: MemberRepository
) {

    @GetMapping("/")
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