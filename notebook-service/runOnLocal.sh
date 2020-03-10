#!/usr/bin/env bash
mvn package -Dmaven.test.skip=true
java -jar target/notebook-1.0.jar
