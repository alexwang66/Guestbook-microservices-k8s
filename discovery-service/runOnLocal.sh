#!/usr/bin/env bash
mvn package
java -jar target/discovery-service.jar
