FROM openjdk:11-jdk as builder

WORKDIR /project

COPY . . 

RUN ["./gradlew", "build"]

FROM openjdk:11-jre-slim-buster

WORKDIR /app

COPY --from=builder /project/build/libs/kotlin_test_project-*-SNAPSHOT.jar /app/kotlin_test_project-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/kotlin_test_project-SNAPSHOT.jar"]