
FROM openjdk:21

RUN mkdir  -p /app

VOLUME /app

WORKDIR /app

ARG JAR_FILE=/target/movie-rental-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} movie-rental.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","movie-rental.jar"]