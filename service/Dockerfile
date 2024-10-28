FROM openjdk:21-jdk-slim
ARG JAVA_APP=target/service-0.0.1-SNAPSHOT.jar
COPY ${JAVA_APP} social_app.jar
ENTRYPOINT ["java", "-jar", "social_app.jar"]