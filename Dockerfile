FROM openjdk:21-jdk-slim AS final
WORKDIR /build/payment-service

COPY target/payment-service.jar app.jar


EXPOSE ${N_TLS_PORT} ${TLS_PORT} ${DEBUG_PORT}

ENTRYPOINT java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:${DEBUG_PORT}  app.jar

