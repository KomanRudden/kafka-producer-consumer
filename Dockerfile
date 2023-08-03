FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kafka-producer-consumer-1.jar
ENTRYPOINT ["java","-jar","/kafka-producer-consumer-1.jar"]