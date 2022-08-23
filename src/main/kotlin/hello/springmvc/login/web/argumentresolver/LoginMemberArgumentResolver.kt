package hello.springmvc.login.web.argumentresolver

import hello.springmvc.login.domain.member.Member
import hello.springmvc.login.web.SessionConst
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class LoginMemberArgumentResolver: HandlerMethodArgumentResolver {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        log.info("supportsParameter 실행")

        val hasParameterAnnotation = parameter.hasParameterAnnotation(Login::class.java)
        val hasMemberType = Member::class.java.isAssignableFrom(parameter.parameterType)

        //@Login 애노테이션이 있으면서 Member 타입이면 true
        return hasParameterAnnotation && hasMemberType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        log.info("resolveArgument 실행")

        val request = webRequest.nativeRequest as HttpServletRequest
        val session = request.getSession(false) ?: return  null

        return session.getAttribute(SessionConst.LOGIN_MEMBER)
    }


}