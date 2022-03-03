FROM adoptopenjdk/openjdk11:jre-11.0.13_8-alpine

EXPOSE 8081

ADD target/BOOTHW_5_2-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]