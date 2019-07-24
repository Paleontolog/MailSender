FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD /target/app.jar app.jar
ADD src/main/resources/database/EmbeddedDb.mv.db  /src/main/resources/database/EmbeddedDb.mv.db
ADD src/main/resources/database/create-db.sql /src/main/resources/database/create-db.sql
ENTRYPOINT ["java","-jar","app.jar"]

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} /target/app.jar
#ADD /target/app.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

##RUN apk add --no-cache bash

#FROM openjdk:8-jdk-alpine
#EXPOSE 8080
#ADD /target/app.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#EXPOSE 8080
#ADD /target/app.jar app.jar
#ADD target/dependency/database/create-db.sql  /target/dependency/database/create-db.sql
#ADD target/dependency/database/EmbeddedDb.mv.db  /target/dependency/database/EmbeddedDb.mv.db
#ENTRYPOINT ["java","-jar","app.jar"]
