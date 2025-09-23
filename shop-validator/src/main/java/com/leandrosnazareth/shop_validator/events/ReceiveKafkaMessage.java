package com.leandrosnazareth.shop_validator.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import com.leandrosnazareth.shop_validator.dto.ShopDTO;
import com.leandrosnazareth.shop_validator.dto.ShopItemDTO;
import com.leandrosnazareth.shop_validator.model.Product;
import com.leandrosnazareth.shop_validator.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {
    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(ShopDTO shopDTO) {
        try {
            log.info("Compra recebida no tópico: {}.",
                    shopDTO.getIdentifier());
            boolean success = true;
            for (ShopItemDTO item : shopDTO.getItems()) {
                Product product = null;
                try {
                    product = productRepository.findByIdentifier(item.getProductIdentifier());
                } catch (DataAccessException dae) {
                    log.error("Erro ao acessar o banco para produto {}: {}",
                            item.getProductIdentifier(), dae.getMessage());
                    log.debug("Stack:", dae);
                    // marca como erro e interrompe processamento dessa compra
                    shopError(shopDTO);
                    success = false;
                    break;
                }

                if (!isValidShop(item, product)) {
                    shopError(shopDTO);
                    success = false;
                    break;
                }
            }
            if (success) {
                shopSuccess(shopDTO);
            }
        } catch (Exception e) {
            log.error("Erro no processamento da compra {}",
                    shopDTO.getIdentifier(), e);
            // garante que um evento ERROR seja enviado em caso de exceção não esperada
            try {
                shopError(shopDTO);
            } catch (Exception ex) {
                log.error("Falha ao enviar evento de erro para a compra {}", shopDTO.getIdentifier(), ex);
            }
        }
    }

    // valida se a compra possui algum erro
    private boolean isValidShop(
            ShopItemDTO item,
            Product product) {
        // retorna true somente se o produto existe E tiver quantidade suficiente
        return product != null && product.getAmount() >= item.getAmount();
    }

    // Envia uma mensagem para o Kafka indicando erro na compra
    private void shopError(ShopDTO shopDTO) {
        log.info("Erro no processamento da compra {}.",
                shopDTO.getIdentifier());
        shopDTO.setStatus("ERROR");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
    }

    // Envia uma mensagem para o Kafka indicando sucesso na compra
    private void shopSuccess(ShopDTO shopDTO) {
        log.info("Compra {} efetuada com sucesso.",
                shopDTO.getIdentifier());
        shopDTO.setStatus("SUCCESS");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
    }
}