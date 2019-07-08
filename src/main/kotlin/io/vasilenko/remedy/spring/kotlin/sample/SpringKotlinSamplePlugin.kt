package io.vasilenko.remedy.spring.kotlin.sample

import com.bmc.arsys.api.Value
import com.bmc.arsys.pluginsvr.plugins.ARFilterAPIPlugin
import com.bmc.arsys.pluginsvr.plugins.ARPluginContext
import com.bmc.thirdparty.org.slf4j.LoggerFactory
import io.vasilenko.remedy.spring.kotlin.sample.service.PluginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
open class SpringKotlinSamplePlugin : ARFilterAPIPlugin() {

    private val log = LoggerFactory.getLogger(SpringKotlinSamplePlugin::class.java)

    private lateinit var applicationContext: AnnotationConfigApplicationContext
    private lateinit var service: PluginService

    @Autowired
    fun setService(service: PluginService) {
        this.service = service
    }

    override fun initialize(context: ARPluginContext?) {
        applicationContext = AnnotationConfigApplicationContext(SpringKotlinSamplePlugin::class.java)
        applicationContext.autowireCapableBeanFactory.autowireBean(this)
        log.info("initialized")
    }

    override fun filterAPICall(context: ARPluginContext?, inputValues: MutableList<Value>?): List<Value> {
        val name = inputValues?.first().toString()
        log.info("name: $name")
        val greeting = service.greeting(name = name)
        log.info("greeting: $greeting")
        return listOf(Value(greeting))
    }

    override fun terminate(context: ARPluginContext?) {
        applicationContext.close()
    }
}
