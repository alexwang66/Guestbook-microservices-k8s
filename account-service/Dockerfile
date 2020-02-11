FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wangq@jfrog.com>
ADD target/account-service.jar account-service.jar
ENTRYPOINT ["java", "-jar", "/account-service.jar"]
EXPOSE 2222