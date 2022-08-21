package hello.springmvc.login.web.filter

import hello.springmvc.login.web.SessionConst
import org.slf4j.LoggerFactory
import org.springframework.util.PatternMatchUtils
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginCheckFilter: Filter {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        val whitelist = arrayOf("/", "/members/add", "/login", "/logout", "/css/*")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val requestURI = httpRequest.requestURI

        try {
            log.info("인증 체크 필터 시작 {}", requestURI)

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI)
                val session = httpRequest.getSession(false)

                if (session?.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI)
                    //로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=$requestURI")

                    return
                }
            }

            chain?.doFilter(request, response)
        } catch (e: Exception) {
            throw e
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI)
        }
    }

    private fun isLoginCheckPath(requestURI: String): Boolean {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI)
    }
}