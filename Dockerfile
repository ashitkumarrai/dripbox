FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/dripbox-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]