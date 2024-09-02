# Nota - Problema com Criação Exchange
Caso pedidos.v2.pedido-criado não seja criado automaticamente, execute o seguinte comando no PowerShell:

```powershell
Invoke-WebRequest -Uri "http://localhost:15672/api/exchanges/%2f/pedidos.v1.pedido-criado" `
    -Method Put `
    -Headers @{ "Content-Type" = "application/json" } `
    -Credential (New-Object PSCredential("rabbitmq", (ConvertTo-SecureString "rabbitmq" -AsPlainText -Force))) `
    -Body '{"type":"fanout"}'
```

# Problemas Conectividade
Problemas com conectividade entre os containers e o Spring Boot, verifique se o container do RabbitMQ está rodando e se o Spring Boot está conseguindo se conectar a ele. Para isso, verifique o arquivo application.properties do Spring Boot, desative manualmente o RabbitMQ no Services
