FROM --platform=linux/amd64 openjdk:23-slim-bookworm

WORKDIR /app

COPY target/prjx-0.0.1-SNAPSHOT.jar /app/prjx-0.0.1-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "prjx-0.0.1-SNAPSHOT.jar"]