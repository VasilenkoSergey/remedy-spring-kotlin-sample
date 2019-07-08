package io.vasilenko.remedy.spring.kotlin.sample.service.impl

import io.vasilenko.remedy.spring.kotlin.sample.service.PluginService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
open class PluginServiceImpl : PluginService {

    @Value("\${greeting}")
    private lateinit var greeting: String

    override fun greeting(name: String): String {
        return "$greeting $name"
    }
}
