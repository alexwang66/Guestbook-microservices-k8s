#!/usr/bin/env bash

kubectl delete -f kube-deploy/discovery.yaml
kubectl delete -f kube-deploy/gateway.yaml
kubectl delete -f kube-deploy/account.yaml
kubectl delete -f kube-deploy/zipkin.yaml
