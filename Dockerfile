# https://spring.io/guides/gs/spring-boot-docker/
#FROM openjdk:11
FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp

ARG JAR_FILE=rest/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]