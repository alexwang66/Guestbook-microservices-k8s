#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/zipkin.yaml
kubectl create -f ../kube-deploy/zipkin.yaml
