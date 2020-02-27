FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wq@jfrogchina.com>
ADD target/discovery-service-1.0.jar discovery-service.jar
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]
EXPOSE 8761