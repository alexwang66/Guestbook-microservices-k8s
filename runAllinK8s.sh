#!/usr/bin/env bash

kubectl delete -f kube-deploy/discovery.yaml
kubectl delete -f kube-deploy/gateway.yaml
kubectl delete -f kube-deploy/account.yaml
kubectl delete -f kube-deploy/zipkin.yaml

cd discovery
./buildAndPushImage.sh

#
cd ./account-service/
./buildAndPushImage.sh

cd ../gateway-service/
./buildAndPushImage.sh

cd ../zipkin-service/
./buildAndPushImage.sh

cd ..
kubectl create -f kube-deploy/config.yaml
kubectl create -f kube-deploy/discovery.yaml
kubectl create -f kube-deploy/account.yaml
kubectl create -f kube-deploy/gateway.yaml
kubectl create -f kube-deploy/zipkin.yaml

