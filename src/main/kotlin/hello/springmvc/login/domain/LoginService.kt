package hello.springmvc.login.domain

import hello.springmvc.login.domain.member.Member
import hello.springmvc.login.domain.member.MemberRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val memberRepository: MemberRepository
) {

    fun login(
        loginId: String,
        password: String
    ): Member? {
        return memberRepository.findByLoginId(loginId)?.let {
            if (it.password == password) it else null
        }
    }
}