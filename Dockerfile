FROM eclipse-temurin:latest
WORKDIR /app
COPY target/sigachat-1.0.0.jar app.jar
expose 8080
CMD ["java", "-jar", "app.jar"]