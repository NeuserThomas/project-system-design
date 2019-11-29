#!/bin/bash

docker network create test_network || true
docker rm mySQLdb || true
docker run --name=mySQLdb --network=test_network -p 3306:3306 -d --env="MYSQL_ROOT_PASSWORD=ThePassword" --env="MYSQL_DATABASE=Day"  mysql:5.7
