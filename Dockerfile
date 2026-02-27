FROM maven:3.9.12-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/*.jar app.jar
ENV JAVA_OPTS="-Xms128m -Xmx256m"
EXPOSE 9999
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
