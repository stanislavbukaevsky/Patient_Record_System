FROM openjdk:17
WORKDIR /application
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} patient-record-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "patient-record-system.jar"]