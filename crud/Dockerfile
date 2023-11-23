FROM maven:3.8.4-openjdk-17-slim AS build


COPY . /home/app


WORKDIR /home/app


RUN mvn clean package -s settings.xml -DskipTests


FROM openjdk:17-slim


COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
