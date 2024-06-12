FROM openjdk:21-jdk-bookworm
VOLUME /tmp
COPY target/springbootdemo-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /app.jar --debug