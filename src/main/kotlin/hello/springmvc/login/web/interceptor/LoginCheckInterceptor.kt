package hello.springmvc.login.web.interceptor

import hello.springmvc.login.web.SessionConst
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginCheckInterceptor: HandlerInterceptor {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        val requestURI = request.requestURI

        log.info("인증 체크 인터셉터 실행 {}", requestURI)
        val session = request.getSession(false)

        if (session?.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청")
            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=$requestURI")
            return false
        }

        return true
    }
}