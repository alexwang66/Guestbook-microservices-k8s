#!/usr/bin/env bash


#mvn -f discovery-service/pom.xml package -Dmaven.test.skip=true
#java -jar discovery-service/target/discovery-service-1.0.jar
#mvn -f account-service/pom.xml package -Dmaven.test.skip=true
#java -jar account-service/target/account-service.jar
#
#
#mvn -f gateway-service/pom.xml package -Dmaven.test.skip=true
#java -jar gateway-service/target/gateway-service.jar
#
#mvn -f zipkin-service/pom.xml package -Dmaven.test.skip=true
#java -jar zipkin-service/target/zipkin-service-1.0.jar
#


 java -jar discovery-service/target/discovery-service-1.0.jar
 java -jar account-service/target/account-service.jar
 java -jar gateway-service/target/gateway-service.jar
 java -jar zipkin-service/target/zipkin-service-1.0.jar