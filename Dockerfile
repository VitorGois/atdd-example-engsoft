# Definir a imagem base
FROM openjdk:17-jdk-slim

# Instalar o Maven
RUN apt-get update && \
    apt-get install -y maven

# Copiar o código-fonte para a imagem
COPY . /app

# Definir o diretório de trabalho
WORKDIR /app

# Baixar as dependências do Maven
RUN mvn dependency:go-offline -B

# Construir o projeto com o Maven
RUN mvn clean package

# Comando para iniciar a aplicação
CMD ["java", "-jar", "target/coursesapi-0.0.1-SNAPSHOT.jar"]