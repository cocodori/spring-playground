package hello.springmvc.login.web.session

import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SessionManager {

    companion object {
        const val SESSION_COOKIE_NAME = "mySessionId"
        private val sessionStore = ConcurrentHashMap<String, Any>()
    }

    fun createSession(
        value: Any,
        response: HttpServletResponse
    ) {
        val sessionId = "${UUID.randomUUID()}"
        sessionStore[sessionId] = value

        val mySessionCookie = Cookie(SESSION_COOKIE_NAME, sessionId)

        response.addCookie(mySessionCookie)
    }

    fun getSession(request: HttpServletRequest): Any? {
        val sessionCookie = findCookie(request, SESSION_COOKIE_NAME)

        return sessionCookie?.let { sessionStore[it.value] }
    }

    fun expire(request: HttpServletRequest) {
        val sessionCookie = findCookie(request, SESSION_COOKIE_NAME)
        sessionCookie?.let {
            sessionStore.remove(it.value)
        }
    }

    private fun findCookie(
        request: HttpServletRequest,
        sessionCookieName: String
    ): Cookie? =
        if (request.cookies == null) {
            null
        } else {
            request.cookies.find { it.name == sessionCookieName }
        }
}