FROM openjdk:8u181-jdk-alpine
ARG workdir=/sending-service
VOLUME ${workdir}
WORKDIR ${workdir}
ADD sending-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]