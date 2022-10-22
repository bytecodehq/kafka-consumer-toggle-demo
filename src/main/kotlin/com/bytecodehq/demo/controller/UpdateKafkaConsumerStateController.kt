package com.bytecodehq.demo.controller

import com.bytecodehq.demo.repository.KafkaConsumerStateRepository
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafkaConsumer/update")
class UpdateKafkaConsumerStateController(
    private val kafkaConsumerStateRepository: KafkaConsumerStateRepository
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun toggleKafkaConsumer(
        @RequestBody updateKafkaConsumerStateRequest: UpdateKafkaConsumerStateRequest
    ): ResponseEntity<Void> {
        kafkaConsumerStateRepository.saveConsumerState(
            consumerId = updateKafkaConsumerStateRequest.consumerId,
            state = updateKafkaConsumerStateRequest.desiredState
        )
        return ResponseEntity.ok().build()
    }
}
