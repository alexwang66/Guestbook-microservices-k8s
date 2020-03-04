FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wq237wq@gmail.com>
ADD target/notebook-1.0.jar notebook.jar
ENTRYPOINT ["java", "-jar", "/notebook.jar"]
EXPOSE 8080