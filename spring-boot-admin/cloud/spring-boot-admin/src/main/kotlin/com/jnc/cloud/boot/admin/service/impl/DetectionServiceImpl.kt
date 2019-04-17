package com.jnc.cloud.boot.admin.service.impl

import com.alibaba.fastjson.JSONObject
import com.jnc.cloud.boot.admin.service.DetectionService
import com.jnc.cloud.boot.admin.service.NotifierService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class DetectionServiceImpl : DetectionService {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var notifierServices: List<NotifierService>

    @Value("\${eureka.client.service-url.defaultZone}")
    lateinit var defaultZone: String

    private val logger = LoggerFactory.getLogger(DetectionServiceImpl::class.java)

    // 缓存服务列表及状态
    internal val serverMap = HashMap<String, String>()

    @Async
    override fun doDetectionOnServer() {
        logger.info("开始执行服务状态检查...")
        try {
            val json = restTemplate.getForObject("$defaultZone/apps", String::class.java)
            val jsonObj = JSONObject.parseObject(json)!!
            val applications = jsonObj.getJSONObject("applications").getJSONArray("application")
            // 记录当前服务列表
            val thisServerSet = HashSet<String>()
            // 1.判断当前服务列表中没有UP实例的服务
            applications.forEach { application ->
                val newit = (application as JSONObject)
                val serverName = newit.getString("name").toLowerCase()
                val lastInstance = newit.getJSONArray("instance").findLast { instance -> (instance as JSONObject).getString("status") == "UP" }
                // 没有UP实例的服务
                if (lastInstance == null) {
                    // 相比上一次检查结果，有所不同的，将会提醒
                    if (serverMap[serverName] == null || serverMap[serverName] == "UP") {
                        logger.info("相比上一次检查结果，有所不同的，将会提醒")
                        serverMap[serverName] = "DOWN"
                        logger.info("发送消息 => $serverName is DOWN")
                        notifierServices.forEach {
                            it.doNotify(serverName)
                        }
                    }
                } else {
                    serverMap[serverName] = "UP"
                }
                // 记录当前服务列表
                thisServerSet.add(serverName)
            }
            // 2.判断从当前服务列表中消失的服务
            serverMap.keys.forEach { serverName ->
                // 当前集合不包含的历史集合
                if (!thisServerSet.contains(serverName)) {
                    logger.info("当前集合不包含的历史集合")
                    if (serverMap[serverName] == "UP") {
                        logger.info("相比上一次检查结果，有所不同的，将会提醒")
                        serverMap[serverName] = "DOWN"
                        logger.info("发送消息 => $serverName is DOWN")
                        notifierServices.forEach {
                            it.doNotify(serverName)
                        }
                    }
                }
            }

            logger.info("完成服务状态检查！")
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error(e.message, e)
        }
    }

}