# MailSender

mvn clean package

mvn dockerfile:build

mvn verify

mvnw dockerfile:push

docker run -p 8080:8080 -t paleontolog/demo
