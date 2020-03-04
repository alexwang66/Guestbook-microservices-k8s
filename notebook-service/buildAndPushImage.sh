#!/usr/bin/env bash

mvn package

docker build -t art.local:8081/docker-local/notebook-k8s/notebook-service:latest .

docker push art.local:8081/docker-local/notebook-k8s/notebook-service:latest