package hello.proxy.pureproxy.proxy

import hello.proxy.pureproxy.proxy.code.CacheProxy
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient
import hello.proxy.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {

    @Test
    fun cacheProxyTest() {
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatternClient(cacheProxy)

        client.execute()
        client.execute()
        client.execute()
    }

    @Test
    fun noProxyTest() {
        val realSubject = RealSubject()
        val client = ProxyPatternClient(realSubject)
        client.execute()
        client.execute()
        client.execute()
    }
}