package com.leandronazareth.kafka_api.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.leandronazareth.kafka_api.dto.ShopDTO;

import lombok.RequiredArgsConstructor;

// Indica que esta classe é um serviço gerenciado pelo Spring
@Service
// Gera automaticamente um construtor com os campos finais
@RequiredArgsConstructor
public class KafkaClient {
    // Injeta o KafkaTemplate para enviar mensagens ao Kafka
    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;
    // Nome do tópico Kafka para onde as mensagens serão enviadas
    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    // Método responsável por enviar a mensagem para o tópico Kafka
    public void sendMessage(ShopDTO msg) {
        kafkaTemplate.send(SHOP_TOPIC_NAME, msg);
    }
}
