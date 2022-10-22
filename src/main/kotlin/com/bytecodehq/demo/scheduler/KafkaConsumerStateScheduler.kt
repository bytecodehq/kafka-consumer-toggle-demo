package com.bytecodehq.demo.scheduler

import com.bytecodehq.demo.consumer.SampleConsumer
import com.bytecodehq.demo.model.KafkaConsumerState
import com.bytecodehq.demo.repository.KafkaConsumerStateRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
@EnableScheduling
class KafkaConsumerStateScheduler(
    private val listenerEndpointRegistry: KafkaListenerEndpointRegistry,
    private val kafkaConsumerStateRepository: KafkaConsumerStateRepository
) {

    companion object {
        private val logger = LoggerFactory.getLogger(KafkaConsumerStateScheduler::class.java)
    }

    @Scheduled(
        initialDelay = 0,
        fixedDelayString = "10000"
    )
    fun toggleSampleConsumer() {
        val containerId = SampleConsumer.SAMPLE_CONSUMER_ID
        val container = listenerEndpointRegistry.getListenerContainer(containerId)
            ?: throw IllegalStateException("Kafka consumer with id $containerId not found")

        val desiredConsumerState = kafkaConsumerStateRepository.getConsumerState(containerId) ?: KafkaConsumerState.START

        when (desiredConsumerState) {
            KafkaConsumerState.START -> container.start()
            KafkaConsumerState.STOP -> container.stop()
            KafkaConsumerState.PAUSE -> container.pause()
            KafkaConsumerState.RESUME -> container.resume()
        }

        logger.info("Current consumer: $containerId state is $desiredConsumerState")
    }
}