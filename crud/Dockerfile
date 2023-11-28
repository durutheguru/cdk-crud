FROM maven:3.8.4-openjdk-17 AS build


WORKDIR /home/app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY . .


RUN mvn clean package -s settings.xml -DskipTests


FROM openjdk:17-slim


COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
