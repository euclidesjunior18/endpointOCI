FROM openjdk:8-jdk-alpine
RUN cd && mkdir target && chmod 777 -r /target
COPY ./target/endpoint-0.0.1-SNAPSHOT.jar /target/
ENTRYPOINT ["java", ".jar", "/target/endpoint-0.0.1-SNAPSHOT.jar"]