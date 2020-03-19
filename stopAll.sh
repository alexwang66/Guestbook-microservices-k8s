#!/usr/bin/env bash

kubectl delete -f kube-deploy/discovery.yaml
kubectl delete -f kube-deploy/gateway.yaml
kubectl delete -f kube-deploy/guestbook.yaml
kubectl delete -f kube-deploy/zipkin.yaml


kill -9 $(lsof -n -i4TCP:8761 | grep LISTEN  | awk '{ print $2 }');
kill -9 $(lsof -n -i4TCP:2222 | grep LISTEN  | awk '{ print $2 }');
kill -9 $(lsof -n -i4TCP:9411 | grep LISTEN  | awk '{ print $2 }');
kill -9 $(lsof -n -i4TCP:8765 | grep LISTEN  | awk '{ print $2 }');