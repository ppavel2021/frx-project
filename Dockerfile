FROM openjdk:8
ADD frx-project-core/target/frx-project-core-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]