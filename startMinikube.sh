#!/usr/bin/env bash

minikube start --cpus 4 --memory 8192

minikube ssh  << EOF
  sudo echo "192.168.100.178  art.local mysql-server zipkin-server" >> /etc/hosts
EOF