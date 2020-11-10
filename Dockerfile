FROM openjdk:8-jdk-alpine
LABEL maintainer="diegolopezcdm@gmail.com"
WORKDIR /workspace
ADD target/*.jar app.jar

ENV MYSQL_HOST="replaceme"
#ENV clientId="2k1tvf4bro2vdn6m3dlhp1aadf"

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
EXPOSE 8080