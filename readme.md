
# 部署方式 1：开发者环境部署：			

## 1.1硬件配置

8C 16GB 机器一台，或者 2 台 4C 8G 机器。

## 1.2 一键编译打包 Java 项目

下载代码：git clone https://github.com/alexwang66/Guestbook-microservices-k8s.git

### 依赖下载，打包
在代码根目录中执行命令：
`cd Guestbook-microservices-k8s`
`mvn package`

默认 mvn package 会从 maven 中央仓库进行下载，速度较慢，建议配置阿里云的 maven 仓库下载依赖。

## 1.3 本地运行 Java 项目	

### 配置/etc/hosts 文件

➜  ~ cat /etc/hosts
127.0.0.1 eureka-server zipkin-server

增加本地对 `eureka-server`和`zipkin-server` 域名的解析。

### 本地运行
在代码根目录中执行./runAll.sh，选择 Y


|  微服务   | 访问路径  |
|  ----  | ----  |
| Discovery Service | http://localhost:8761 |
| Guestbook service  | http://localhost:2222/guestbook/ |
| Gateway service  | http://localhost:8765/guestbook/|
| Zipkin service  | http://localhost:9411 |


## 1.4 停止本地运行的 Java 项目
在代码根目录中执行./stopAll.sh



# 部署方式 2： Kubernetes 部署

## 2.1 配置免费本地 Docker 镜像中心 JFrog Container Registry 

1. 创建$JFROG_HOME环境变量。
  
    `export $JFROG_HOME=/Users/yourUser/.jfrog/JFROG_HOME`
2. 创建 JCR 工作目录	
```
    mkdir -p $JFROG_HOME/jcr/var/etc/
    cd $JFROG_HOME/jcr/var/etc/
    touch ./system.yaml
    chown -R 1030:1030 $JFROG_HOME/jcr/var
	
```
3. 启动镜像

    `docker run --name artifactory-jcr  -v $JFROG_HOME/jcr/var:/var/opt/jfrog/artifactory -p 8081:8081 -p 8082:8082 docker.bintray.io/jfrog/artifactory-jcr:latest`

4. 登录
	`docker login art.local:8081 -uadmin -ppassw0rd`


注意：企业用户推荐使用 JFrog Artifactory 企业版,下载链接 wiki.jfrog.com		
	

## 2.2 启动 Minikube



`curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.30.0/minikube-darwin-amd64 && chmod+x minikube &&  sudo mv minikube/usr/local/bin/`

`minikube start --cpus 4 --memory 8192`

Minikube 安装细节参考：https://yq.aliyun.com/articles/221687/

配置本地镜像中心域名
```
minikube ssh
su
sudo echo "your-artifactory-ip art.local zipkin-server" >> /etc/hosts
docker login  art.local:8081 -uadmin -ppassw0rd
```
Add insecure registry for minikube:
	~/.minikube/machines/minikube/config.json

```
"InsecureRegistry": [
                "10.96.0.0/12",
                "art.local:8081"
            ],
```
## 2.3 创建Kubernetes镜像秘钥
`kubectl create secret docker-registry regcred-local --docker-server=art.local:8081 --docker-username=admin --docker-password=passw0rd --docker-email=wq237wq@gmail.com`			


## 2.4 构建并推送镜像
构建所有服务的镜像，并推送到镜像仓库：./updateImages.sh


## 2.5

部署服务到 Kubernetes： ./runAll.sh，选择 N
微服务访问


|  微服务   | 访问路径  |
|  ----  | ----  |
| Discovery Service | http://minikube ip:31002 |
| Guestbook service  | http://minikube ip:30222/guestbook/ |
| Gateway service  | http://minikube ip:30333/guestbook/|
| Zipkin service  | http://minikube ip:30411 |


​	
​			
​			
# Helm部署

## 运行
`discovery 
 		helm install -f discovery/values.yaml discovery -n discovery`

`Guestbook
    helm install -f guestbook/values.yaml guestbook -n guestbook `
## 打包
	helm package discovery 
## 上传到 Artifactory
	curl -uadmin:apikey -T /Users/qing/Documents/code/sample-microservices-k8s/kube-deploy/charts/discovery-0.1.0.tgz "http://localhost:8081/artifactory/helm/discovery-0.1.0.tgz"
## 删除Helm Chart
	helm del --purge discovery



# 部署方式 3： Docker 部署

## 3.1 配置免费本地 Docker 镜像中心 JFrog Container Registry 

同2.1的操作

## 3.2 构建并推送镜像 

同2.4的操作

## 3.3 以Docker方式运行 

在代码根目录中执行./runDocker.sh


| 微服务            | 访问路径                         |
| ----------------- | -------------------------------- |
| Discovery Service | http://localhost:8761            |
| Guestbook service | http://localhost:2222/guestbook/ |
| Gateway service   | http://localhost:8765/guestbook/ |
| Zipkin service    | http://localhost:9411            |

## 3.4 停止Docker运行的项目

在代码根目录中执行./stopDocker.sh





