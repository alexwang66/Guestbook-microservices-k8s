#!/usr/bin/env bash

kubectl delete -f ../kube-deploy/guestbook.yaml
kubectl create -f ../kube-deploy/guestbook.yaml
