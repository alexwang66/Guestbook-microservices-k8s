docker stop zipkin-service
docker stop geteway-service
docker stop guestbook-service
docker stop discovery-service

sleep 10

docker rm zipkin-service
docker rm geteway-service
docker rm guestbook-service
docker rm discovery-service
