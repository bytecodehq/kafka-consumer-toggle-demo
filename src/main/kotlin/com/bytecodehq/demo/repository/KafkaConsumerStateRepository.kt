package com.bytecodehq.demo.repository

import com.bytecodehq.demo.model.KafkaConsumerState
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class KafkaConsumerStateRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    fun saveConsumerState(
        consumerId: String,
        state: KafkaConsumerState
    ) {
        val sql = "INSERT INTO kafka_consumer_state (id, state) VALUES('$consumerId', '${state.name}') ON CONFLICT " +
            "(id) DO UPDATE SET state = EXCLUDED.state;"

        jdbcTemplate.execute(sql)
    }

    fun getConsumerState(
        consumerId: String
    ): KafkaConsumerState? {
        val sql = "SELECT state from kafka_consumer_state where id='$consumerId';";

        return jdbcTemplate.query(sql) { rs, _ ->
            rs.getString("state").let {
                KafkaConsumerState.valueOf(it)
            }
        }.firstOrNull()
    }
}