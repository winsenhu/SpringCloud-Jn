package com.jnc.service1

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 * \server启动类
 *
 * Created by LeeKul on 2018/10/16
 **/
@SpringBootApplication
@EnableDiscoveryClient
class Service1Application {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Service1Application::class.java, *args)
        }
    }
}