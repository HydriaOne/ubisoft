FROM gradle:jdk11 AS builder
WORKDIR /opt/ubisoft
COPY . /opt/ubisoft
RUN gradle microBundle

FROM openjdk:11
COPY --from=builder /opt/ubisoft/build/libs/ROOT-microbundle.jar /opt/application.jar
EXPOSE 8080
CMD java -jar /opt/application.jar