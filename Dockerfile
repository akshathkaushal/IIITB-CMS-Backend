FROM openjdk:11
EXPOSE 8090
COPY ./target/spe-backend.jar ./
WORKDIR ./
ENTRYPOINT ["java","-jar","spe-backend.jar"]