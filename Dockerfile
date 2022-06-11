# Build stage
FROM maven:3.6.3-jdk-11-slim AS prod
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
# package stage
COPY --from=prod ./target/spe-backend.jar ./
EXPOSE 8090
WORKDIR ./
ENTRYPOINT ["java","-jar","spe-backend.jar"]