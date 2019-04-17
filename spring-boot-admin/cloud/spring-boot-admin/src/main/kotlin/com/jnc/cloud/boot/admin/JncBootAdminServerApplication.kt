package com.jnc.cloud.boot.admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
class JncBootAdminServerApplication {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(JncBootAdminServerApplication::class.java, *args)
        }
    }

    @Configuration
    class SecurityPermitAllConfig : WebSecurityConfigurerAdapter() {
        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable()
        }
    }

}