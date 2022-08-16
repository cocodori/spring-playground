package hello.springmvc.login.web.member

import hello.springmvc.login.domain.member.Member
import hello.springmvc.login.domain.member.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/members")
class MemberController(
    private val memberRepository: MemberRepository
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/get")
    fun addForm(
        @ModelAttribute member: Member
    ): String {
        return "members/addMemberForm"
    }

    @PostMapping("/add")
    fun save(
        @Valid @ModelAttribute member: Member,
        bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors())
            return "members/addMemberForm"

        memberRepository.save(member)

        return "redirect:/"
    }
}