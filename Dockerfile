FROM maven:3.6.2-jdk-13 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:13-alpine
COPY --from=build /usr/src/app/target/rpsls-app-0.0.1-SNAPSHOT.jar /usr/app/rpsls-app-0.0.1-SNAPSHOT.jar
EXPOSE 4567
ENTRYPOINT ["java","--enable-preview", "-jar", "/usr/app/rpsls-app-0.0.1-SNAPSHOT.jar"]