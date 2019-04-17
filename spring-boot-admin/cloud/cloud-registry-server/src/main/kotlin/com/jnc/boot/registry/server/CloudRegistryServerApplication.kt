package com.jnc.boot.registry.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer


/**
 * 服务注册
 *
 * Created by candy on 2017/4/26.
 */
@SpringBootApplication
@EnableEurekaServer
open class CloudRegistryServerApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(CloudRegistryServerApplication::class.java, *args)
        }
    }
}
