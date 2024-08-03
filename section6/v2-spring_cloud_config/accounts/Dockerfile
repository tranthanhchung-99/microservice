# base image
FROM openjdk:17-jdk-slim

# info create by
MAINTAINER TranThanhChung-99

# copy file jar from target to docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# start file jar in docker
ENTRYPOINT ["java","-jar", "accounts-0.0.1-SNAPSHOT.jar"]