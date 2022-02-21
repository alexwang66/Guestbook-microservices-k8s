#!/usr/bin/env bash

docker build -t art.local:8081/docker-dev-local/guestbook-service:latest .

docker push art.local:8081/docker-staging-local/guestbook-service:latest