package com.bytecodehq.demo.controller

import com.bytecodehq.demo.model.KafkaConsumerState

data class UpdateKafkaConsumerStateRequest(
    val consumerId: String,
    val desiredState: KafkaConsumerState
)
