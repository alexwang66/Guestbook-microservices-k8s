FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wq@jfrogchina.com>
ADD target/zipkin-service-1.0.jar zipkin-service.jar
ENTRYPOINT ["java", "-jar", "/zipkin-service.jar"]
EXPOSE 9411