#!/usr/bin/env bash
mvn package
java -jar target/gateway-service.jar
