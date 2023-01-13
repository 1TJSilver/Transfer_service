FROM openjdk:8-jdk-alpine

EXPOSE 5500

COPY target/HW_7_4_1_1_Transfer_Service-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]