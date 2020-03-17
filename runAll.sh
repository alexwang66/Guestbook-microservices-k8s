#!/usr/bin/env bash


while true; do
    read -p "Run project as java app on local environment or run on K8S?, Y=Java, N=K8S" yn
    case $yn in
        [Yy]* )
            nohup java -jar discovery-service/target/discovery-service-1.0.jar  &
            sleep 3s # Waits eureka start for 3 seconds.
            nohup java -jar guestbook-service/target/guestbook-service.jar  &
            nohup java -jar gateway-service/target/gateway-service.jar  &
            nohup java -jar zipkin-service/target/zipkin-service-1.0.jar  &
         break;;
        [Nn]* )
            kubectl delete -f kube-deploy/discovery.yaml
            kubectl delete -f kube-deploy/gateway.yaml
            kubectl delete -f kube-deploy/guestbook.yaml
            kubectl delete -f kube-deploy/zipkin.yaml


            kubectl create -f kube-deploy/discovery.yaml
            kubectl create -f kube-deploy/guestbook.yaml
            kubectl create -f kube-deploy/gateway.yaml
            kubectl create -f kube-deploy/zipkin.yaml
        exit;;
        * ) echo "Please answer yes or no.";;
    esac
done



