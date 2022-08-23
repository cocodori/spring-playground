package hello.springmvc.login.web

import hello.springmvc.login.web.argumentresolver.LoginMemberArgumentResolver
import hello.springmvc.login.web.filter.LogFilter
import hello.springmvc.login.web.interceptor.LogInterceptor
import hello.springmvc.login.web.interceptor.LoginCheckInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.Filter

@Configuration
class WebConfig: WebMvcConfigurer {

    override fun addArgumentResolvers(
        resolvers: MutableList<HandlerMethodArgumentResolver>
    ) {
        resolvers.add(LoginMemberArgumentResolver())
    }

    override fun addInterceptors(
        registry: InterceptorRegistry
    ) {
        registry.addInterceptor(LogInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/css/**", "/*.ico", "/error")

        registry.addInterceptor(LoginCheckInterceptor())
            .order(2)
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/", "/members/add", "/login", "/logout",
                "/css/**", "/*.ico", "/error"
            )
    }

//    @Bean
    fun loginCheckFilter(): FilterRegistrationBean<Filter> {
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
        filterRegistrationBean.filter = LogFilter()
        filterRegistrationBean.order = 2
        filterRegistrationBean.addUrlPatterns("/*")

        return filterRegistrationBean
    }

//    @Bean
    fun logFilter(): FilterRegistrationBean<Filter> {
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
        filterRegistrationBean.filter = LogFilter()
        filterRegistrationBean.order = 1
        filterRegistrationBean.addUrlPatterns("/*")

        return filterRegistrationBean
    }
}