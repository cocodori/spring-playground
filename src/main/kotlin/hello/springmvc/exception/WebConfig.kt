package hello.springmvc.exception

import hello.springmvc.exception.resolver.MyHandlerExceptionResolver
import hello.springmvc.exception.resolver.UserHandlerExceptionResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//@Configuration
class WebConfig: WebMvcConfigurer {

    override fun extendHandlerExceptionResolvers(resolvers: MutableList<HandlerExceptionResolver>) {
        resolvers.add(MyHandlerExceptionResolver())
        resolvers.add(UserHandlerExceptionResolver())
    }
}