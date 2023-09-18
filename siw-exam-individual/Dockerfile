FROM openjdk:11
EXPOSE 8080
WORKDIR /usr/app

ARG JAR_NAME=siw-exam-individual-1.0.0.jar
ENV JAR_NAME=$JAR_NAME

COPY target/$JAR_NAME .
ENTRYPOINT exec java -jar $JAR_NAME