# MailSender

mvn clean package

mvn dockerfile:build

mvn verify

docker push paleontolog/demo:tagname

mvnw dockerfile:push

docker run -p 8080:8080 -t paleontolog/demo
