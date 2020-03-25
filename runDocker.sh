docker run --name discovery-service -d -p 8761:8761 art.local:8081/docker-local/guestbook-microservices-k8s/discovery-service:latest
docker run --name zipkin-service -d -p 9411:9411 art.local:8081/docker-local/guestbook-microservices-k8s/zipkin-service:latest
sleep 10
docker run --name guestbook-service -d -p 2222:2222 --link discovery-service:eureka-server --link zipkin-service:zipkin-server art.local:8081/docker-local/guestbook-microservices-k8s/guestbook-service:latest
sleep 10
docker run --name geteway-service -d -p 8765:8765 --link discovery-service:eureka-server --link zipkin-service:zipkin-server art.local:8081/docker-local/guestbook-microservices-k8s/gateway-service:latest
