FROM java:8
EXPOSE 8081
ARG JAR_FILE
ADD target/${JAR_FILE} /waiter-service.jar
ENTRYPOINT ["java", "-jar", "/waiter-service.jar"]