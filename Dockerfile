# Dockerfile

FROM maven:3.5-jdk-8-alpine as BUILD
COPY . /data
RUN mvn -f /data/pom.xml clean package

FROM openjdk:8-jre-alpine
ENV PORT 8080
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE ${PORT}
COPY --from=BUILD /data/target/*.jar /ROOT.jar
CMD ["java", "-jar", "ROOT.jar"]
