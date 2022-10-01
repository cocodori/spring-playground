package hello.proxy.pureproxy.decorator

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient
import hello.proxy.pureproxy.decorator.code.MessageDecorator
import hello.proxy.pureproxy.decorator.code.RealComponent
import hello.proxy.pureproxy.decorator.code.TimeDecorator
import org.junit.jupiter.api.Test

class DecoratorPatternTest {

    @Test
    fun decorator2() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val timeDecorator = TimeDecorator(messageDecorator)

        val client = DecoratorPatternClient(timeDecorator)
        client.execute()
    }

    @Test
    fun decorator1() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val client = DecoratorPatternClient(messageDecorator)
        client.execute()
    }

    @Test
    fun noDecorator() {
        val realComponent = RealComponent()
        val client = DecoratorPatternClient(realComponent)

        client.execute()
    }
}