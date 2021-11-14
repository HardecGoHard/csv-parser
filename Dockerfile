FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
WORKDIR /
COPY target/csv-parser.jar csv-parser.jar
ENTRYPOINT ["java", "-jar", "csv-parser.jar"]
EXPOSE 8080