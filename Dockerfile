FROM openjdk:11
EXPOSE 8090
RUN mvn clean package
COPY ./target/spe-backend.jar ./
WORKDIR ./
ENTRYPOINT ["java","-jar","spe-backend.jar"]