FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/ApiTelefone-1.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
#COMANDO PARA BUILDAR  : docker build -t api-telefone .
#COMANDO PARA RODAR : > docker run --rm api-telefone "+55151552155" "2" "199001"

