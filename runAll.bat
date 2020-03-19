@echo off

echo please enter your enviroment
echo y: run on local environment (default)
echo n: run on k8s environment

set /p env=

if "%env%" == "y" (
  start java -jar discovery-service/target/discovery-service-1.0.jar
  timeout 5
  start java -jar gateway-service/target/gateway-service.jar
  start java -jar guestbook-service/target/guestbook-service.jar
  start java -jar zipkin-service/target/zipkin-service-1.0.jar
) else (
	if "%env%" == "n" (
	  echo kube-deploy/discovery.yaml
      echo kube-deploy/gateway.yaml
      echo kube-deploy/questbook.yaml
      echo kube-deploy/zipkin.yaml
      echo kube-deploy/config.yaml
      echo kube-deploy/discovery.yaml
      echo kube-deploy/questbook.yaml
      echo kube-deploy/gateway.yamlrun
      echo kube-deploy/zipkin.yaml
	) else (
	  echo Error!!! The "%env%" is not a correct input ,please rerun the script and input a correct parameter.
	)
)