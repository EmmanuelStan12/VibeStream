package com.bytebard.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ["com.bytebard"])
class WebConfig {

    @Bean
    fun jsonMessageConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter()
    }

    @Bean
    fun requestMappingHandlerAdapter(messageConverter: MappingJackson2HttpMessageConverter): RequestMappingHandlerAdapter {
        return RequestMappingHandlerAdapter().apply {
            messageConverters = listOf(messageConverter)
        }
    }
}