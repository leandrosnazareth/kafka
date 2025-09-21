Comandos Kafka denntro do container
criar topicos
kafka-topics --create   --topic "nome_topic"   --bootstrap-server localhost:9092   --partitions 1   --replication-factor 1

listar topicos
kafka-topics --list --bootstrap-server localhost:9092

apagar topicos
kafka-topics --delete --topic "nome_topic" --bootstrap-server localhost:9092

retornar detalhes de um tópico especifico
kafka-topics --describe --topic "nome_topic" --bootstrap-server localhost:9092

enviar mensagem
kafka-console-producer --topic "nome_topic" --bootstrap-server localhost:9092

receber consumir mensagem
kafka-console-consumer --topic "nome_topic" --from-beginning --bootstrap-server localhost:9092

definir grupo de consumidores
kafka-console-consumer --topic "nome_topic" --bootstrap-server localhost:9092 --group grupo1

verificar que a mensagem já está no Kafka
kafka-console-consumer --topic SHOP_TOPIC --bootstrap-server localhost:9092 --from-beginning 

listar os grupos de consumidores
kafka-consumer-groups --bootstrap-server localhost:9092 --list

apagar grupo 
kafka-consumer-groups --bootstrap-server localhost:9092 --group grupo1 --delete

reinicializar totalmente o kafka
rm -rf /tmp/kafka-logs /tmp/zookeeper
