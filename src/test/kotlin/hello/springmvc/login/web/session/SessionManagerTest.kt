package hello.springmvc.login.web.session

import hello.springmvc.login.domain.member.Member
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

internal class SessionManagerTest {


    val sessionManager = SessionManager()

    @Test
    fun sessionTest() {
        // 세션 생성
        val response = MockHttpServletResponse()
        val member = Member(
            id = 1,
            loginId = "tester",
            name = "testMember",
            password = "testMember"
        )
        val member2 = Member(
            id = 1,
            loginId = "tester",
            name = "testMember",
            password = "testMember"
        )
        val member3 = Member(
            id = 1,
            loginId = "tester",
            name = "testMember",
            password = "testMember"
        )
        val member4 = Member(
            id = 1,
            loginId = "tester",
            name = "testMember",
            password = "testMember"
        )
        sessionManager.createSession(member, response)
        sessionManager.createSession(member2, response)
        sessionManager.createSession(member3, response)
        sessionManager.createSession(member4, response)

        // 요청에 응답 쿠키 저장
        val request = MockHttpServletRequest()
        request.setCookies(*response.cookies)

        //세션 조회
        val result = sessionManager.getSession(request)
        assertThat(result).isEqualTo(member)

        //세션 만료
        sessionManager.expire(request)
        val expire = sessionManager.getSession(request)
        assertThat(expire).isNull()
    }
}