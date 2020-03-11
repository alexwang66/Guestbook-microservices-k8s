#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/account.yaml
kubectl create -f ../kube-deploy/account.yaml
