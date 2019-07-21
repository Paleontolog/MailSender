FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo-0.0.1-SNAPSHOT.jar"]
RUN apk add --no-cache bash

#FROM java:8
#EXPOSE 8080
#ADD /target/demo-0.0.1-SNAPSHOT.jar demo.jar
#ENTRYPOINT ["java","-jar","demo.jar"]