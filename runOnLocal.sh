#!/usr/bin/env bash


nohup java -jar discovery-service/target/discovery-service-1.0.jar  &
sleep 5s # Waits 5 seconds.
nohup java -jar account-service/target/account-service.jar  &
nohup java -jar gateway-service/target/gateway-service.jar  &
nohup java -jar zipkin-service/target/zipkin-service-1.0.jar  &

