package com.jnc.cloud.boot.admin.service

interface NotifierService {

    /**
     * 验证消息发送有效性，并发送消息
     */
    fun doNotify(serverName: String)

}