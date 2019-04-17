package com.jnc.cloud.boot.admin.config

import com.jnc.cloud.boot.admin.service.DetectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class DetectionConfig {

    @Autowired
    lateinit var detectionService: DetectionService

//    /**
//     * 每隔60秒执行一次,检查服务列表活性情况
//     */
//    @Scheduled(fixedRate = 60000)
//    fun detectionTasks() {
//        detectionService.doDetectionOnServer()
//    }

}