# Build using JDK 15
FROM adoptopenjdk/openjdk15

# FROM ubuntu:latest
# RUN apt-get update && apt-get install -y openjdk-8-jdk

WORKDIR /usr/local/bin/

ADD artifacts .

ENTRYPOINT ["java", "-jar", "project-management.jar"]