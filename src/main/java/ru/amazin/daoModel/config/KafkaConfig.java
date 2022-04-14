package ru.amazin.daoModel.config;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.amazin.daoModel.models.MessageTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:app.properties")
public class KafkaConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value("${kafka.producer.id}")
    private String producerId;

    @Bean
    public Map<String, Object> setKafkaProperties() {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        kafkaProperties.put(ProducerConfig.CLIENT_ID_CONFIG, producerId);
        kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return kafkaProperties;
    }

    @Bean
    public KafkaTemplate<Long, MessageTemplate> kafkaTemplate() {
        KafkaTemplate<Long, MessageTemplate> template =
                new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(setKafkaProperties()));
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}
