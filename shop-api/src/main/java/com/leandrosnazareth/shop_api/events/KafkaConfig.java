package com.leandrosnazareth.shop_api.events;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.leandrosnazareth.shop_api.dto.ShopDTO;

// Indica que esta classe é uma configuração do Spring
@Configuration
public class KafkaConfig {
    // Endereço do servidor Kafka, pode ser configurado via application.properties
    @Value(value = "${kafka.bootstrapAddress:localhost:9092}")
    private String bootstrapAddress;

    // Configuração do Producer para enviar mensagens para o Kafka
    public ProducerFactory<String, ShopDTO> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        // Endereço do servidor Kafka
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        // Serializador da chave (String)
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        // Serializador do valor (JSON)
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        // Identificador do cliente Producer
        props.put(
                ProducerConfig.CLIENT_ID_CONFIG,
                "shop-api");
        return new DefaultKafkaProducerFactory<>(props);
    }

    // Bean do KafkaTemplate para enviar mensagens
    @Bean
    public KafkaTemplate<String, ShopDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Configuração do Consumer para receber mensagens do Kafka
    public ConsumerFactory<String, ShopDTO> consumerFactory() {
        // Deserializador para ShopDTO
        JsonDeserializer<ShopDTO> deserializer = new JsonDeserializer<>(ShopDTO.class);
        Map<String, Object> props = new HashMap<>();
        // Endereço do servidor Kafka
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    // Bean para criar o listener de mensagens Kafka
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShopDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ShopDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}