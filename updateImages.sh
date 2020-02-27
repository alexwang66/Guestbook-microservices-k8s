#!/usr/bin/env bash

cd ./discovery-service/
./buildAndPushImage.sh
#
cd ./account-service/
./buildAndPushImage.sh

cd ../gateway-service/
./buildAndPushImage.sh

cd ../zipkin-service/
./buildAndPushImage.sh


