FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wq@jfrogchina.com>
ADD target/account-service.jar account-service.jar
ENTRYPOINT ["java", "-jar", "/account-service.jar"]
EXPOSE 2222