package com.jnc.cloud.boot.admin.notifier

import com.jnc.messagev2.boot.message.MessageManager
import com.jnc.messagev2.message.SmsMessage
import de.codecentric.boot.admin.server.domain.entities.Instance
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository
import de.codecentric.boot.admin.server.domain.events.InstanceEvent
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.expression.MapAccessor
import org.springframework.expression.ParserContext
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import reactor.core.publisher.Mono

class SmsNotifier(private var repositpry: InstanceRepository) : AbstractStatusChangeNotifier(repositpry), InitializingBean {

    override fun afterPropertiesSet() {
        logger.info("========SmsNotifier 初始化完成！====")
        logger.info("=phoneNumber=$phoneNumber")
        logger.info("=sourceType=$sourceType")
    }

    private val DEFAULT_MESSAGE = "【服务名：#{instance.registration.name}(#{instance.id})  状态：#{event.statusInfo.status}】"

    private val message = SpelExpressionParser().parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION)

    lateinit var phoneNumber: String

    lateinit var sourceType: String

    @Autowired
    lateinit var messageManager: MessageManager


    private val logger = LoggerFactory.getLogger(SmsNotifier::class.java)

    override fun doNotify(instanceEvent: InstanceEvent, instance: Instance): Mono<Void> {
        logger.info("==进入短信事件==")
        return Mono.fromRunnable {
            val phones = phoneNumber.split(",")
            for (phone in phones) {
                val smsMessage = SmsMessage()
                smsMessage.sourceType = sourceType
                smsMessage.mobile = phone
                smsMessage.signName = "生态圈共享系统"
                val params = HashMap<String, String>()
                params["sevice"] = getMessage(instanceEvent, instance) ?: "NO MESSAGE"
                logger.info("==$sourceType ; $phone ; ${params["sevice"]}")
                smsMessage.params = params
                messageManager.sendSmsMessage(smsMessage)
            }
        }
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun shouldNotify(event: InstanceEvent?, instance: Instance?): Boolean {
        if (event is InstanceStatusChangedEvent) {
//            if (event.statusInfo.isDown) {
            return true
//            }
        }
        return super.shouldNotify(event, instance)
    }

    override fun notify(event: InstanceEvent?): Mono<Void> {
        if (event is InstanceStatusChangedEvent) {
            logger.info(">>1>" + event.statusInfo.status)
            logger.info(">>2>" + repositpry.find(event.instance).block()?.toString())
        }
        return super.notify(event)
    }

    private fun getMessage(event: InstanceEvent, instance: Instance): String? {
        val root = HashMap<String, Any>()
        root["event"] = event
        root["instance"] = instance
        val context = StandardEvaluationContext(root)
        context.addPropertyAccessor(MapAccessor())
        return message.getValue(context, String::class.java)
    }

}
