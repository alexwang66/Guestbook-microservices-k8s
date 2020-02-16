#!/usr/bin/env bash
mvn package
java -jar target/zipkin-service-1.0.jar
