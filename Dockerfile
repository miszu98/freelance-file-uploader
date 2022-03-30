FROM openjdk:17
ARG JAR_FILE=target/file-uploader.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

