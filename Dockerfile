FROM alpine/git as source
LABEL maintainer="luizcomz@gmail.com"
WORKDIR /app
RUN git clone https://github.com/iquee/music-rest-api.git

FROM maven:3.5-jdk-8-alpine as build
LABEL maintainer="luizcomz@gmail.com"
WORKDIR /app
COPY --from=source /app/music-rest-api /app
RUN mvn install -DskipTests

FROM openjdk:8-jdk-alpine
LABEL maintainer="luizcomz@gmail.com"
VOLUME /tmp
EXPOSE 8080
COPY --from=build /app/target/music-rest-api-1.0.0.jar /app/
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/music", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/music-rest-api-1.0.0.jar"]