# shop-validator

Serviço Spring Boot responsável por validar mensagens de pedidos provenientes de tópicos Kafka. Projeto de exemplo para integrar Kafka com validações e armazenamento simples (H2).

## Resumo da aplicação
A aplicação é ao mesmo tempo um consumidor e um produtor: ela consome informações de compra de um tópico Kafka, valida/processa essas informações e publica o resultado do processamento (validação aprovada/reprovada) em outro tópico. O nome desta aplicação é `shop-validator`, pois sua responsabilidade principal é validar se a compra é válida ou não.

- Tópico de entrada (exemplo): `orders`
- Tópico de saída (exemplo): `orders-validated`
- Comportamento: consumir evento de pedido -> validar regras de negócio -> persistir resultado em H2 -> publicar evento de resultado.

## Visão geral
- Valida mensagens de pedidos (orders) recebidas via Kafka.
- Persiste resultados básicos em banco H2 (runtime).
- Usado para demonstração/integração com outros serviços (ex: shop-api).

## Requisitos
- Java 17
- Maven 3.6+
- Kafka (para integração local ou em container)

## Estrutura do projeto
- src/main/java — código-fonte Spring Boot
- src/main/resources — configurações (application.properties)
- pom.xml — dependências e build

## Configuração rápida
Defina as propriedades de conexão do Kafka em `application.properties` (exemplo):
- spring.kafka.bootstrap-servers=localhost:9092
- spring.kafka.consumer.group-id=shop-validator
- tópicos esperados (ex.: `orders`, `orders-validated`)

## Como rodar localmente
1. Iniciar Kafka (local ou via Docker).
2. Build:
   mvn clean package
3. Executar:
   mvn spring-boot:run
   ou
   java -jar target/shop-validator-0.0.1-SNAPSHOT.jar

## Testes
- Testes unitários com `mvn test`.
- Integração com Kafka pode ser feita usando `spring-kafka-test` ou ambientes Docker Compose.

## Contribuição
Abra issues ou pull requests com melhorias. Mantenha commits claros e documente alterações relevantes.

## Licença
Sem licença especificada — adicione um arquivo LICENSE se necessário.
