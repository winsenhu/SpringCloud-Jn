package com.jnc.cloud.boot.admin.service

interface DetectionService {

    /**
     * 验证服务，仅验证服务可用性
     */
    fun doDetectionOnServer()

}