
# 开发者环境：			

## 配置本地开发环境Idea

@TODO


# 配置系统环境变量

在/etc/hosts 添加：
127.0.0.1  localhost config registry zipkin-server



## 本地运行 Java 项目	
在代码根目录中执行./runAll.sh

|  微服务   | 访问路径  |
|  ----  | ----  |
| Discovery Service | localhost:8761 |
| Account service  | localhost:2222 |
| Gateway service  | localhost:8761 |
| Zipkin service  | localhost:9411 |


# GuestBook 微服务
## 项目介绍

	
	
	• 微服务
		○ Spring cloud config
			§ /application-{profile-name}.yml
			§ http://localhost:8888/application-native.yml
			§ http://localhost:8888/application-native/auth-service.yml
		○ Docker
					
		○ Guestbook
			§ http://localhost:8765/actuator/routes/details/
		○ Gateway service
			§ http://localhost:8765/api/account/
		○ Zipkin
			§ http://localhost:9411/traces/6279bf32d894731f?serviceName=account-service
		○ YAML
			§ 通过${ZIPKIN_SERVER}引用本地系统变量~/.bash_profile

# Kubernetes 运行项目

## 配置免费本地 Docker 镜像中心 JFrog Container Registry 
	
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
	

# Docker
```
构建 Docker 镜像
docker build -t art.local:8081/docker-local/notebook-microservices-k8s/notebook-service:latest .
推送镜像
docker push art.local:8081/docker-local/notebook-microservices-k8s/notebook-service:latest
		
		
```
# Jenkins

# Kubernetes
		○ Kubernetes镜像秘钥
			§ kubectl create secret docker-registry regcred-local --docker-server=art.local:8081 --docker-username=admin --docker-password=passw0rd --docker-email=wq237wq@gmail.com
		○ Minikube
			§ 启动
				□ minikube start --cpus 4 --memory 8192
			§ 配置本地镜像中心域名
				sudo echo "192.168.100.178 art.local mysql-server zipkin-server" >> /etc/hosts
				□ docker login  art.local:8081 -uadmin -ppassw0rd
				
			
		○ 微服务访问
			§ http://192.168.99.100:31002/
			§ http://192.168.99.100:30222/
			§ 注册中心内部域名：eureka-server
		○ Helm
			§ helm install -f values.yaml ../discovery 
			§ Package
				□ helm package discovery 
			§ 上传到 Artifactory
				□ curl -uadmin:AP8N3k1u3tygcz8QeLM3Yge4n1A -T /Users/qing/Documents/code/sample-microservices-k8s/kube-deploy/charts/discovery-0.1.0.tgz "http://localhost:8081/artifactory/helm/discovery-0.1.0.tgz"
			§ 启动 服务
				□ discovery 
					® helm install -f discovery/values.yaml discovery -n discovery
				□ Account
					® helm install -f account/values.yaml account -n account
			§ 删除服务
				□ helm del --purge account
		○ 配置文件
			§ Deployment.yaml 文件里设置环境变量
	• Mysql
		○ 
		○ 登录
			§ 启动
				docker run -p 3306:3306 --name mysql \
				-v /opt/mysql-data/conf:/etc/mysql \
				-v /opt/mysql-data/mysql/logs:/var/log/mysql \
				-v /opt/mysql-data/mysql/data:/var/lib/mysql \
				-e MYSQL_ROOT_PASSWORD=password \
				-d mysql/mysql-server:5.6
				
			§ 配置
				□  GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;
				□ FLUSH PRIVILEGES;
		
		○ 容器
			§ 客户端登录
				□ /usr/local/mysql/bin/mysql -uroot -ppassword -h 127.0.0.1
			§ 登录容器
				□ docker exec -it 7ce2538c7a30 /bin/bash
		○ K8s
			§ Mount volumn, minikube
			§ kubectl exec -it mysql-5447ff55d5-kxsfq bash
TODO

