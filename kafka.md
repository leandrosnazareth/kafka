Comandos Kafka denntro do container
# CRIAR TÓPICOS
kafka-topics --create --topic nome_topic --bootstrap-server localhost:9092 --partitions 1  --replication-factor 1
### CRIAR TÓPICOS COM MAIS DE UMA PARTIÇÃO
kafka-topics --create --topic nome_topic --bootstrap-server localhost:9092 --partitions 6

# LISTAR TÓPICOS
### listar todos os topicos criados e internos
kafka-topics --list --bootstrap-server localhost:9092
### listar somente topicos criados
kafka-topics --bootstrap-server localhost:9092 --list | grep -v '^_'

# DESCREVER TÓPICOS (RETORNAR INFORMAÇÕES DO TÓPICO)
kafka-topics --describe --topic SHOP_TOPIC --bootstrap-server localhost:9092

# APAGAR TÓPICOS
kafka-topics --delete --topic nome_topic --bootstrap-server localhost:9092

# RETONAR DETALHES DO TÓPICO
kafka-topics --describe --topic "nome_topic" --bootstrap-server localhost:9092

# ENVIAR MENSAGEM PARA O TÓPICO
kafka-console-producer --topic "nome_topic" --bootstrap-server localhost:9092

# RECEBER CONSUMIR MENSAGEM DO TÓPICO
kafka-console-consumer --topic "nome_topic" --from-beginning --bootstrap-server localhost:9092

# CRIAR GRUPO DE CONSUMIDORES
kafka-console-consumer --topic "nome_topic" --bootstrap-server localhost:9092 --group grupo1

# VERIFICAR SE A MENSAGEM FOI ENVIADA E ESTÁ NO TÓPICO
kafka-console-consumer --topic SHOP_TOPIC --bootstrap-server localhost:9092 --from-beginning 

# LISTAR OS GRUPOS DE CONSUMIDORES
kafka-consumer-groups --bootstrap-server localhost:9092 --list

# APAGAR GRUPO DE CONSUMIDORES
kafka-consumer-groups --bootstrap-server localhost:9092 --group grupo1 --delete

# REINICIALIZAR E REDEFINIR KAFKA
rm -rf /tmp/kafka-logs /tmp/zookeeper
