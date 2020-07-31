#!/usr/bin/env bash

docker build -t art.local:8081/docker-local/guestbook-microservices-k8s/guestbook-service:latest .

docker push art.local:8081/docker-local/guestbook-microservices-k8s/guestbook-service:latest