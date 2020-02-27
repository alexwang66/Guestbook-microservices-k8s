FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wq@jfrogchina.com>
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]
EXPOSE 8765