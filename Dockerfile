FROM openjdk:8-jdk-alpine
RUN cd / && mkdir target && chmod 777 -R /target
COPY ./out/artifacts/endpoint_jar/endpoint.jar /target/
ENTRYPOINT ["java", ".jar", "/out/artifacts/endpoint_jar/endpoint.jar"]