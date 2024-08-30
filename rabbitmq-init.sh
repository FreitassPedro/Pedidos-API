#!/bin/sh

# Cria o arquivo de configuração do RabbitMQ
echo "deprecated_features.permit.management_metrics_collection = false" > /etc/rabbitmq/rabbitmq.conf

# Inicia o RabbitMQ em segundo plano
rabbitmq-server &

# Aguarda até que o RabbitMQ esteja totalmente iniciado
until rabbitmqctl status; do
  echo "Aguardando RabbitMQ iniciar..."
  sleep 5
done

# Adiciona o usuário administrador e define permissões
rabbitmqctl add_user rabbitmq rabbitmq
rabbitmqctl set_user_tags rabbitmq administrator
rabbitmqctl set_permissions -p / rabbitmq ".*" ".*" ".*"
