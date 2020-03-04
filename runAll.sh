#!/usr/bin/env bash


while true; do
    read -p "Run on local java environment or run on K8S?, Y=Java, N=K8S" yn
    case $yn in
        [Yy]* )
            nohup java -jar discovery-service/target/discovery-service-1.0.jar  &
            nohup java -jar account-service/target/account-service.jar  &
            nohup java -jar gateway-service/target/gateway-service.jar  &
            nohup java -jar zipkin-service/target/zipkin-service-1.0.jar  &
         break;;
        [Nn]* )
            kubectl delete -f kube-deploy/discovery.yaml
            kubectl delete -f kube-deploy/gateway.yaml
            kubectl delete -f kube-deploy/account.yaml
            kubectl delete -f kube-deploy/zipkin.yaml

            kubectl create -f kube-deploy/config.yaml
            kubectl create -f kube-deploy/discovery.yaml
            kubectl create -f kube-deploy/account.yaml
            kubectl create -f kube-deploy/gateway.yaml
            kubectl create -f kube-deploy/zipkin.yaml
        exit;;
        * ) echo "Please answer yes or no.";;
    esac
done



