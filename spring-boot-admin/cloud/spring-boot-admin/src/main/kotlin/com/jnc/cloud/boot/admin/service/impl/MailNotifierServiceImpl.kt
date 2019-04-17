package com.jnc.cloud.boot.admin.service.impl

import com.jnc.cloud.boot.admin.service.NotifierService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class MailNotifierServiceImpl : NotifierService {

    @Value("\${spring.boot.admin.notify.email.enabled}")
    lateinit var enabled: String

    @Value("\${spring.boot.admin.notify.email.interval}")
    lateinit var interval: String

    // 缓存消息列表及提醒时间
    internal val serverMap = HashMap<String, LocalDateTime>()

    @Autowired
    lateinit var mailSender: MailSender

    private val logger = LoggerFactory.getLogger(MailNotifierServiceImpl::class.java)

    @Async
    override fun doNotify(serverName: String) {
        if (enabled == "true") {
            val time = serverMap[serverName]
            // 不存在记录则直接发送
            if (time == null) {
                runMailServer(serverName)
                serverMap[serverName] = LocalDateTime.now()
            } else {
                val duration = Duration.between(time, LocalDateTime.now())
                val minutes = duration.toMinutes()//相差的分钟数
                logger.info("[邮件]存在发送记录，间隔($minutes/$interval)")
                // 存在发送记录，但是间隔大于配置的分钟数
                if (minutes > interval.toLong()) {
                    runMailServer(serverName)
                    serverMap[serverName] = LocalDateTime.now()
                }
            }
        }
    }

    @Value("\${spring.boot.admin.notify.email.to}")
    lateinit var toMails: String

    @Value("\${spring.boot.admin.notify.email.from}")
    lateinit var fromMail: String

    fun runMailServer(serverName: String) {
        val toMailArray = toMails.split(",")
        for (toMail in toMailArray) {
            val message = SimpleMailMessage()
            message.text = " 服务名：$serverName  状态：DOWN "
            message.subject = " 服务名：$serverName  状态：DOWN "
            message.setTo(toMail)
            message.from = fromMail
            try {
                mailSender.send(message)
                logger.info("邮件[to $toMail]发送成功！")
            } catch (e: Exception) {
                e.printStackTrace()
                logger.error("邮件[to $toMail]发送失败!", e)
            }
        }
    }

}