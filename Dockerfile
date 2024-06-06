FROM openjdk:17-alpine
COPY target/bank-transactional-ms-0.0.1-SNAPSHOT.jar bank-transactional-ms-0.0.1-SNAPSHOT.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","bank-transactional-ms-0.0.1-SNAPSHOT.jar"]
