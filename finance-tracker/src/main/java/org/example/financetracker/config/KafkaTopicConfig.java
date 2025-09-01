package org.example.financetracker.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaTopicConfig {
    private List<TopicConfig> topics;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put("offsets.topic.replication.factor", "1");
        configs.put("transaction.state.log.replication.factor", "1");
        configs.put("transaction.state.log.min.isr", "1");
        return new KafkaAdmin(configs);

    }

    @Bean
    public List<NewTopic> createTopics() {
        //создаем топики из конфигурации
        return topics.stream().map(topic -> new NewTopic(topic.getName(), topic.getPartitions(), topic.getReplicationFactor()))
                .toList();
    }

    @Getter
    @Setter
    public static class TopicConfig {
        private String name;
        private int partitions;
        private short replicationFactor;

    }

}
