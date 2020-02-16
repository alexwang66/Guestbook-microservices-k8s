#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/discovery.yaml
kubectl create -f ../kube-deploy/discovery.yaml
