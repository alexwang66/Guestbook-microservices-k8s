#!/usr/bin/env bash
mvn package
java -jar target/guestbook-service.jar
