FROM openjdk:8u121-jre-alpine


ADD simpleapp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -Djava.net.preferIPv4Stack=true -jar /app.jar" ]