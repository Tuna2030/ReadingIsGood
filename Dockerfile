FROM openjdk:11
ADD target/readingisgood.jar readingisgood.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","readingisgood.jar"]