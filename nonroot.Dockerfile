FROM openjdk:21-rc-oracle as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw package -Dmaven.test.skip

# Use Java 21 in runtime as well
FROM openjdk:21-rc-oracle
RUN groupadd -r appgroup && useradd -r -g appgroup appuser
USER appuser
WORKDIR /app
EXPOSE 8080

COPY --from=builder /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
