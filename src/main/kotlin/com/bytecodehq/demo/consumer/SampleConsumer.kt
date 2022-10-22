package com.bytecodehq.demo.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class SampleConsumer {

    companion object {
        const val SAMPLE_CONSUMER_ID = "sample-consumer"
        private val logger = LoggerFactory.getLogger(SampleConsumer::class.java)
    }

    @KafkaListener(
        id = SAMPLE_CONSUMER_ID,
        topics = ["sample-topic"],
        groupId = "bytecodehq",

        // To avoid starting the consumer during application startup.
        // We must start the consumer depending on the current desired state.
        autoStartup = "false"
    )
    fun onMessage(
        record: ConsumerRecord<String, String>
    ) {
       logger.info("Consumed message -> Key=${record.key()} Value=${record.value()}")
    }
}
