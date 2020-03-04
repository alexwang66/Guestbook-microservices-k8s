#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/notebook.yaml
kubectl create -f ../kube-deploy/notebook.yaml
