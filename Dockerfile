# Definir a imagem base
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar para dentro do container
COPY target/*.jar /app/coursesapi-0.0.1-SNAPSHOT.jar

# Expõe a porta 8080 da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=8080 -jar coursesapi-0.0.1-SNAPSHOT.jar
