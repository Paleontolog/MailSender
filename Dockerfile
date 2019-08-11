FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/app.jar app.jar
ADD /target/classes/schedulefiles/schedule.csv /schedulefiles/schedule.csv
ADD /target/dependency/application.yml /database/application.yml
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/database/application.yml"]
#ENTRYPOINT ["java", "-jar","app.jar"]

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} /target/app.jar
#ADD /target/app.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

##RUN apk add --no-cache bash

