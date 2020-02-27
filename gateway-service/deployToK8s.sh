#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/gateway.yaml
kubectl create -f ../kube-deploy/gateway.yaml
