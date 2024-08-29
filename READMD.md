# Nota - Problema com Criação Exchange
Caso pedidos.v2.pedido-criado não seja criado automaticamente, execute o seguinte comando no PowerShell:

````powershell
Invoke-WebRequest -Uri "http://localhost:15672/api/exchanges/%2f/pedidos.v1.pedido-criado" `
    -Method Put `
    -Headers @{ "Content-Type" = "application/json" } `
    -Credential (New-Object PSCredential("rabbitmq", (ConvertTo-SecureString "rabbitmq" -AsPlainText -Force))) `
    -Body '{"type":"fanout"}'
