FROM java:8-jdk-alpine
COPY ./build/libs/htmlParser-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","htmlParser-1.0-SNAPSHOT.jar"]
