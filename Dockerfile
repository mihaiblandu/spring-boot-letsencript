FROM openjdk:8-jdk-alpine
WORKDIR /
COPY ./build/libs/app-0.0.1-SNAPSHOT.jar app.jar
COPY ./GeoLite2-ASN_20210316/  GeoLite2-ASN_20210316/
COPY ./keystore keystore
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
EXPOSE 8443