#!/usr/bin/env bash
mvn package
java -jar target/discovery-service-1.7.jar
