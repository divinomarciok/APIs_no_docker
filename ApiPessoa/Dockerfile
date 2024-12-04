FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/ApiPessoa-1.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

# COMANDO PARA BUILDAR : docker build -t api-pessoa-app .
# COMANDO PARA RODAR O CONTAINER :  docker run --rm api-pessoa-app "12345" "Divino Marcioi" "ana.silva@example.com" "1995-02-15"
