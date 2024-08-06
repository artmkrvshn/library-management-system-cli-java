FROM eclipse-temurin:21-jdk as BUILDER
WORKDIR /dev-company
COPY gradle ./gradle
COPY src ./src
COPY gradlew build.gradle settings.gradle ./
RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon -i clean bootJar

FROM eclipse-temurin:21-jre
WORKDIR /dev-company
COPY --from=BUILDER /dev-company/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["echo", "test"]
ENTRYPOINT ["java", "-jar", "app.jar"]