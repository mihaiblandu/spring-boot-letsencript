FROM openjdk:8-jdk-alpine
WORKDIR /
COPY app-0.0.1-SNAPSHOT.jar app.jar
COPY ../GeoLite2-ASN_20210316/  GeoLite2-ASN_20210316/
COPY ../keystore keystore
ENTRYPOINT ["java","-jar","/app.jar --server.ssl.key-store=./keystore --server.port=443 --server.http.port=80"]
EXPOSE 80
EXPOSE 443