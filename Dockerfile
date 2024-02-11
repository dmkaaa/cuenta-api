FROM eclipse-temurin:21.0.1_12-jdk as build

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY settings.gradle.kts .

RUN chmod +x gradlew
RUN ./gradlew wrapper

COPY build.gradle.kts .
RUN ./gradlew dependencies

COPY src ./src
RUN ./gradlew clean build

FROM eclipse-temurin:21.0.1_12-jre-alpine

COPY --from=build /app/build/libs/cuenta-0.0.1.jar /app/cuenta.jar

ENTRYPOINT ["sh","-c","java -jar /app/cuenta.jar"]
