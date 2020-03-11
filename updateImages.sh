#!/usr/bin/env bash

cd ./discovery-service/
./buildAndPushImage.sh
#
cd ../guestbook-service/
./buildAndPushImage.sh

cd ../gateway-service/
./buildAndPushImage.sh

cd ../zipkin-service/
./buildAndPushImage.sh


