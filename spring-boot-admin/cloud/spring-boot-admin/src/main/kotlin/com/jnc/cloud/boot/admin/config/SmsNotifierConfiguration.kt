//package com.jnc.cloud.boot.admin.config
//
//import com.jnc.cloud.boot.admin.notifier.SmsNotifier
//import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration.CompositeNotifierConfiguration
//import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration.NotifierTriggerConfiguration
//import de.codecentric.boot.admin.server.domain.entities.InstanceRepository
//import org.springframework.boot.autoconfigure.AutoConfigureBefore
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//@ConditionalOnProperty(
//        prefix = "spring.boot.admin.notify.sms",
//        name = ["phone-number"]
//)
//@AutoConfigureBefore(value = [NotifierTriggerConfiguration::class, CompositeNotifierConfiguration::class])
//class SmsNotifierConfiguration {
//
//    @Bean
//    @ConditionalOnMissingBean
//    @ConfigurationProperties(prefix = "spring.boot.admin.notify.sms")
//    fun smsNotifier(repository: InstanceRepository): SmsNotifier {
//        return SmsNotifier(repository)
//    }
//
//}