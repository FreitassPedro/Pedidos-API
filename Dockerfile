# Use a imagem base do OpenJDK 21
FROM openjdk:21-jdk

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR da sua aplicação para o contêiner
COPY target/pedidosApi-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Exponha a porta que a aplicação Spring Boot está escutando
EXPOSE 8080
