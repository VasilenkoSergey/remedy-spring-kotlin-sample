package io.vasilenko.remedy.spring.kotlin.sample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@PropertySource(value = ["classpath:application.properties"])
open class PropertiesConfig {

    @Bean
    open fun defaultPropertyConfigurer() = PropertySourcesPlaceholderConfigurer()
}
