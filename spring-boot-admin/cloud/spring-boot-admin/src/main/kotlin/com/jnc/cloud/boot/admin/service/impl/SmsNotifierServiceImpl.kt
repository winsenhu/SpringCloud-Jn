package com.jnc.cloud.boot.admin.service.impl

import com.jnc.cloud.boot.admin.service.NotifierService

import com.jnc.messagev2.boot.message.MessageManager
import com.jnc.messagev2.message.SmsMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class SmsNotifierServiceImpl : NotifierService {

    @Value("\${spring.boot.admin.notify.sms.enabled}")
    lateinit var enabled: String

    @Value("\${spring.boot.admin.notify.sms.interval}")
    lateinit var interval: String

    // 缓存消息列表及提醒时间
    internal val serverMap = HashMap<String, LocalDateTime>()

    @Autowired
    lateinit var messageManager: MessageManager

    private val logger = LoggerFactory.getLogger(SmsNotifierServiceImpl::class.java)

    @Async
    override fun doNotify(serverName: String) {
        if (enabled == "true") {
            val time = serverMap[serverName]
            // 不存在记录则直接发送
            if (time == null) {
                runSmsServer(serverName)
                serverMap[serverName] = LocalDateTime.now()
            } else {
                val duration = Duration.between(time, LocalDateTime.now())
                val minutes = duration.toMinutes()//相差的分钟数
                logger.info("[短信]存在发送记录，间隔($minutes/$interval)")
                // 存在发送记录，但是间隔大于配置的分钟数
                if (minutes > interval.toLong()) {
                    runSmsServer(serverName)
                    serverMap[serverName] = LocalDateTime.now()
                }
            }
        }
    }

    @Value("\${spring.boot.admin.notify.sms.phone-number}")
    lateinit var phoneNumber: String

    @Value("\${spring.boot.admin.notify.sms.source-type}")
    lateinit var sourceType: String

    fun runSmsServer(serverName: String) {
        val phones = phoneNumber.split(",")
        for (phone in phones) {
            val smsMessage = SmsMessage()
            smsMessage.sourceType = sourceType
            smsMessage.mobile = phone
            smsMessage.signName = "生态圈共享系统"
            val params = HashMap<String, String>()
            params["sevice"] = " 服务名：$serverName  状态：DOWN "
            logger.info("==$sourceType ; $phone ; ${params["sevice"]}")
            smsMessage.params = params
            messageManager.sendSmsMessage(smsMessage)
        }
    }

}