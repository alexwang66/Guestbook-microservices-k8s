# Install/Update Minikube 

如需更新minikube，需要更新 minikube 安装包

` minikube delete` 
`删除现有VirtualBox虚机`
`删除 ~/.minikube 目录缓存的文件`
重新创建 minikube 环境

###Mac
curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.30.0/minikube-darwin-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

###Linux
curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.30.0/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

minikube start --registry-mirror=https://registry.docker-cn.com


# Create Docker regisgtry secret in K8s

## dev space
kubectl create secret docker-registry regcred-local --docker-server=art.local:8081 --docker-username=admin --docker-password=passw0rd --docker-email=wq237wq@gmail.com
## staging space
kubectl create secret docker-registry regcred-local --docker-server=art.local:8081 --docker-username=admin --docker-password=passw0rd --docker-email=wq237wq@gmail.com --namespace=staging
## prod space
kubectl create secret docker-registry regcred-local --docker-server=art.local:8081 --docker-username=admin --docker-password=passw0rd --docker-email=wq237wq@gmail.com --namespace=prod